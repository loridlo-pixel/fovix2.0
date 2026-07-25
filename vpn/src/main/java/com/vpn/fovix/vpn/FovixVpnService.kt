package com.vpn.fovix.vpn


import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor
import android.util.Log

import com.vpn.fovix.config.SingBoxConfigBuilder
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







            val server =
                VPNServer(

                    protocol = "vless",

                    address = "ai.noooo.win",

                    port = 443,

                    uuid =
                    "c5c1c20f-691d-4850-988c-ee463f4799ad",

                    sni =
                    "cdn-v1-6a51ff3b.noooo.win",

                    fingerprint = "chrome"

                )








            val config =
                SingBoxConfigBuilder
                    .build(server)





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
                    "ENGINE FAILED"
                )


                FovixDiagnostics.event(
                    FovixEvent.ENGINE_FAILED
                )


                SingBoxNative.stop()


                stopSelf()


                return

            }








            running.set(true)





            FovixDiagnostics.event(
                FovixEvent.ENGINE_STARTED
            )





            Log.e(
                TAG,
                "========== FOVIX CONNECTED =========="
            )



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