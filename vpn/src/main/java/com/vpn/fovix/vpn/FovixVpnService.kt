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


            FovixDiagnostics.event(
                FovixEvent.ENGINE_STOPPED
            )


            running.set(false)


            tunInterface?.close()


            tunInterface = null

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


        FovixDiagnostics.event(
            FovixEvent.VPN_START_COMMAND
        )



        if(running.get())
            return START_STICKY



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
                .setMtu(1500)

                .addAddress(
                    "10.0.0.2",
                    32
                )

                .addRoute(
                    "0.0.0.0",
                    0
                )

                .addDnsServer(
                    "1.1.1.1"
                )



            tunInterface =
                builder.establish()



            if(tunInterface == null) {


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




            val fd =
                tunInterface!!
                    .detachFd()


            tunInterface = null



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



            FovixDiagnostics.event(
                FovixEvent.CONFIG_GENERATED,
                "size=${config.length}"
            )




            FovixDiagnostics.event(
                FovixEvent.ENGINE_START_REQUEST
            )




            val ok =
                SingBoxNative.start(
                    config,
                    fd
                )




            if(!ok) {


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



            Log.e(
                TAG,
                "ENGINE START REQUEST ACCEPTED"
            )



            waitForEngineReady()


        }
        catch(e: Exception) {


            Log.e(
                TAG,
                "VPN ERROR",
                e
            )


            FovixDiagnostics.error(
                "vpn_start",
                e
            )


            SingBoxNative.stop()


            stopSelf()

        }


    }







    private fun waitForEngineReady() {


        Thread {


            val timeout = 5000L


            val startedAt =
                System.currentTimeMillis()



            while(
                System.currentTimeMillis() - startedAt < timeout
            ) {


                try {


                    if(SingBoxNative.isRunning()) {


                        running.set(true)


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
                catch(e: Exception) {


                    FovixDiagnostics.error(
                        "engine_status_check",
                        e
                    )


                    return@Thread

                }


            }



            Log.e(
                TAG,
                "ENGINE READY TIMEOUT"
            )


            FovixDiagnostics.event(
                FovixEvent.ENGINE_FAILED,
                "startup_timeout"
            )


            SingBoxNative.stop()


        }.start()


    }







    override fun onDestroy() {


        Log.e(
            TAG,
            "VPN DESTROY"
        )


        FovixDiagnostics.event(
            FovixEvent.VPN_DESTROYED
        )



        SingBoxNative.stop()



        tunInterface?.close()



        running.set(false)



        super.onDestroy()

    }


}