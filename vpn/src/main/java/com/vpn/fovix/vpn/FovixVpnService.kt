package com.vpn.fovix.vpn


import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor
import android.util.Log

import com.vpn.fovix.vpn.config.FovixVpnConfigProvider
import com.vpn.fovix.diagnostics.FovixDiagnostics
import com.vpn.fovix.diagnostics.FovixEvent

import java.util.concurrent.atomic.AtomicBoolean



class FovixVpnService : VpnService() {


    companion object {


        private const val TAG = "FOVIX"


        private var tunInterface: ParcelFileDescriptor? = null


        private val running =
            AtomicBoolean(false)



        fun stopVPN() {


            Log.e(
                TAG,
                "========== STOP VPN =========="
            )


            running.set(false)


            try {

                SingBoxNative.stop()

            }
            catch(e: Exception){

                Log.e(
                    TAG,
                    "STOP ERROR",
                    e
                )

            }



            try {

                tunInterface?.close()

            }
            catch(e: Exception){}



            tunInterface = null



            FovixDiagnostics.event(
                FovixEvent.ENGINE_STOPPED
            )

        }

    }





    override fun onCreate() {

        super.onCreate()


        Log.e(
            TAG,
            "========== VPN SERVICE CREATED =========="
        )


        FovixDiagnostics.event(
            FovixEvent.VPN_SERVICE_CREATED
        )

    }






    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {


        Log.e(
            TAG,
            "========== VPN START COMMAND =========="
        )


        if(running.get()) {

            Log.e(
                TAG,
                "VPN ALREADY RUNNING"
            )

            return START_STICKY
        }



        startVPN()



        return START_STICKY

    }







    private fun startVPN() {


        try {


            Log.e(
                TAG,
                "CREATE ANDROID TUN"
            )


            FovixDiagnostics.event(
                FovixEvent.TUN_CREATE_STARTED
            )



            val builder =
                Builder()



            builder
    .setSession("FOVIX")
    .setMtu(1400)


    .addAddress(
        "10.0.0.2",
        32
    )


    /*
       Весь трафик отправляем в TUN
    */

    .addRoute(
        "0.0.0.0",
        0
    )


    .addRoute(
        "::",
        0
    )


    .addDnsServer(
        "1.1.1.1"
    )



            tunInterface =
                builder.establish()



            if(tunInterface == null){


                Log.e(
                    TAG,
                    "TUN FAILED"
                )


                FovixDiagnostics.event(
                    FovixEvent.ENGINE_FAILED,
                    "tun_failed"
                )


                stopSelf()

                return

            }





            /*
             * Передаем FD в native.
             *
             * detachFd() передает владение
             * файловым дескриптором sing-box.
             *
             * ParcelFileDescriptor больше
             * не закрываем вручную после этого.
             */


            val fd =
                tunInterface!!
                    .detachFd()



            Log.e(
                TAG,
                "TUN FD=$fd"
            )



            FovixDiagnostics.event(
                FovixEvent.TUN_CREATED,
                "fd=$fd"
            )





            val config =
                FovixVpnConfigProvider.build()



            Log.e(
                TAG,
                "CONFIG SIZE=${config.length}"
            )




            val ok =
                SingBoxNative.start(
                    config,
                    fd
                )



            if(!ok){


                Log.e(
                    TAG,
                    "ENGINE START FAILED"
                )


                FovixDiagnostics.event(
                    FovixEvent.ENGINE_FAILED
                )


                SingBoxNative.stop()


                stopSelf()

                return

            }





            running.set(true)



            Log.e(
                TAG,
                "ENGINE START REQUEST ACCEPTED"
            )



            waitForEngineReady()


        }
        catch(e: Exception){


            Log.e(
                TAG,
                "VPN ERROR",
                e
            )


            SingBoxNative.stop()


            stopSelf()

        }

    }








    private fun waitForEngineReady(){


        Thread {


            val timeout = 5000L


            val start =
                System.currentTimeMillis()



            while(
                System.currentTimeMillis() - start < timeout
            ){


                try {


                    if(
                        SingBoxNative.isRunning()
                    ){


                        FovixDiagnostics.event(
                            FovixEvent.ENGINE_STARTED
                        )


                        Log.e(
                            TAG,
                            "========== FOVIX CONNECTED =========="
                        )


                        return@Thread

                    }



                    Thread.sleep(250)


                }
                catch(e: Exception){


                    Log.e(
                        TAG,
                        "ENGINE CHECK ERROR",
                        e
                    )


                    return@Thread

                }


            }




            Log.e(
                TAG,
                "ENGINE READY TIMEOUT"
            )



            running.set(false)



            SingBoxNative.stop()


        }.start()


    }








    override fun onDestroy(){


        Log.e(
            TAG,
            "VPN DESTROY"
        )


        running.set(false)



        SingBoxNative.stop()



        try {

            tunInterface?.close()

        }
        catch(e: Exception){}



        tunInterface = null



        FovixDiagnostics.event(
            FovixEvent.VPN_DESTROYED
        )



        super.onDestroy()

    }


}