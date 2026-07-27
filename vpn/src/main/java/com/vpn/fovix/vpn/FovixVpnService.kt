package com.vpn.fovix.vpn


import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor
import android.util.Log

import com.vpn.fovix.diagnostics.FovixDiagnostics
import com.vpn.fovix.diagnostics.FovixEvent
import com.vpn.fovix.vpn.config.FovixVpnConfigProvider

import java.util.concurrent.atomic.AtomicBoolean



class FovixVpnService : VpnService() {


    companion object {


        private const val TAG = "FOVIX"


        private var tunInterface: ParcelFileDescriptor? = null


        private val running =
            AtomicBoolean(false)



        fun stopVPN() {


            running.set(false)


            try {

                SingBoxNative.stop()

            } catch (_: Exception) {


            }



            try {

                tunInterface?.close()

            } catch (_: Exception) {


            }


            tunInterface = null

        }

    }







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


                /*
                    Android TUN interface
                 */

                .addAddress(
                    "172.19.0.1",
                    30
                )


                /*
                    Full traffic through VPN
                 */

                .addRoute(
                    "0.0.0.0",
                    0
                )


                /*
                    IPv6 traffic
                 */

                .addRoute(
                    "::",
                    0
                )


                /*
                    DNS
                 */

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





            val config =
                FovixVpnConfigProvider.build()



            Log.e(
                TAG,
                "CONFIG SIZE=${config.length}"
            )



            Log.e(
                "FOVIX_RAW_CONFIG",
                config
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


                SingBoxNative.stop()


                stopSelf()


                return

            }




            running.set(true)



            Log.e(
                TAG,
                "ENGINE STARTED"
            )



        }
        catch(e: Exception) {


            Log.e(
                TAG,
                "VPN ERROR",
                e
            )


            SingBoxNative.stop()


            stopSelf()

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

        } catch (_: Exception) {



        }




        try {

            tunInterface?.close()

        } catch (_: Exception) {



        }



        tunInterface = null



        super.onDestroy()

    }

}