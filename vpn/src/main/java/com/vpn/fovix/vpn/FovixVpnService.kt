package com.vpn.fovix.vpn


import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor
import android.util.Log

import com.vpn.fovix.domain.vpnprofile.VpnProfile
import com.vpn.fovix.diagnostics.FovixDiagnostics
import com.vpn.fovix.diagnostics.FovixEvent
import com.vpn.fovix.vpn.config.FovixVpnConfigProvider


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


import java.util.concurrent.atomic.AtomicBoolean



class FovixVpnService : VpnService() {



    companion object {


        private const val TAG = "FOVIX"



        private var tunInterface:
                ParcelFileDescriptor? = null



        private val running =
            AtomicBoolean(false)



        fun stopVPN() {


            running.set(false)


            try {

                SingBoxNative.stop()

            }
            catch (_: Exception) {


            }



            try {

                tunInterface?.close()

            }
            catch (_: Exception) {


            }



            tunInterface = null


        }



    }





    private var vpnIntent: Intent? = null





    private val serviceScope =
        CoroutineScope(
            SupervisorJob() +
                    Dispatchers.IO
        )








    override fun onCreate() {


        super.onCreate()



        Log.e(
            TAG,
            "VPN CREATED"
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



        vpnIntent = intent



        Log.e(
            TAG,
            "VPN START COMMAND"
        )



        if(running.get()) {


            Log.e(
                TAG,
                "ALREADY RUNNING"
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
                "CREATE TUN"
            )




            val builder =
                Builder()



            builder

                .setSession(
                    "FOVIX"
                )


                .setMtu(
                    1500
                )


                .addAddress(
                    "172.19.0.1",
                    30
                )


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






            if(tunInterface == null) {


                Log.e(
                    TAG,
                    "TUN CREATE FAILED"
                )


                VpnEngine.notifyError(
                    "TUN CREATE FAILED"
                )


                stopSelf()


                return

            }






            Log.e(
                TAG,
                "VPN ESTABLISHED"
            )






            val fd =
                tunInterface!!
                    .detachFd()





            Log.e(
                TAG,
                "TUN FD=$fd"
            )





            val profile =
                getVpnProfile()



            val config =
                FovixVpnConfigProvider.build(
                    profile
                )





            Log.e(
                TAG,
                "CONFIG SIZE=${config.length}"
            )





            serviceScope.launch {


                try {



                    Log.e(
                        TAG,
                        "ENGINE START BACKGROUND"
                    )





                    val started =
                        SingBoxNative.start(
                            config,
                            fd
                        )






                    if(!started) {



                        Log.e(
                            TAG,
                            "ENGINE FAILED"
                        )



                        VpnEngine.notifyError(
                            "ENGINE START FAILED"
                        )



                        SingBoxNative.stop()



                        stopSelf()



                        return@launch

                    }







                    running.set(true)






                    Log.e(
                        TAG,
                        "ENGINE STARTED"
                    )





                    monitorState()





                }
                catch(e: Exception) {



                    Log.e(
                        TAG,
                        "ENGINE ERROR",
                        e
                    )



                    VpnEngine.notifyError(
                        e.message
                            ?: "UNKNOWN ERROR"
                    )



                    SingBoxNative.stop()



                    stopSelf()


                }


            }






        }
        catch(e: Exception) {



            Log.e(
                TAG,
                "VPN ERROR",
                e
            )



            VpnEngine.notifyError(
                e.message
                    ?: "VPN ERROR"
            )



            SingBoxNative.stop()



            stopSelf()


        }



    }












    private fun monitorState() {


        serviceScope.launch {


            while(running.get()) {



                try {



                    val state =
                        SingBoxNative.getState()





                    Log.e(
                        TAG,
                        "ENGINE STATE=$state"
                    )





                    when(state) {



                        "CONNECTED" -> {


                            VpnEngine.notifyConnected(
                                getVpnProfile().name
                            )


                        }



                        "ERROR" -> {


                            VpnEngine.notifyError(
                                "SINGBOX ERROR"
                            )


                        }



                    }




                }
                catch(e: Exception) {



                    Log.e(
                        TAG,
                        "STATE CHECK ERROR",
                        e
                    )

                }






                delay(1000)



            }



        }



    }












    override fun onDestroy() {



        Log.e(
            TAG,
            "VPN DESTROY"
        )



        running.set(false)





        try {


            SingBoxNative.stop()


        }
        catch (_: Exception) {


        }







        try {


            tunInterface?.close()


        }
        catch (_: Exception) {


        }







        tunInterface = null






        serviceScope.cancel()





        super.onDestroy()



    }












    private fun getVpnProfile(): VpnProfile {


        val intent =
            vpnIntent
                ?: throw IllegalStateException(
                    "VPN intent missing"
                )



        return VpnProfile(



            name =
                intent.getStringExtra(
                    "SERVER_NAME"
                )
                    ?: "Unknown",





            country =
                intent.getStringExtra(
                    "SERVER_COUNTRY"
                )
                    ?: "Unknown",





            server =
                intent.getStringExtra(
                    "SERVER_HOST"
                )
                    ?: throw IllegalStateException(
                        "Server missing"
                    ),





            port =
                intent.getIntExtra(
                    "SERVER_PORT",
                    443
                ),





            uuid =
                intent.getStringExtra(
                    "SERVER_UUID"
                )
                    ?: throw IllegalStateException(
                        "UUID missing"
                    ),





            sni =
                intent.getStringExtra(
                    "SERVER_SNI"
                )
                    ?: throw IllegalStateException(
                        "SNI missing"
                    ),





            fingerprint =
                intent.getStringExtra(
                    "SERVER_FP"
                )
                    ?: "chrome"



        )


    }



}