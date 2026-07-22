package com.vpn.fovix.vpn


import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor
import android.util.Log

import com.vpn.fovix.config.SingBoxConfigBuilder

import java.util.concurrent.atomic.AtomicBoolean



class FovixVpnService : VpnService() {


    companion object {

        private const val TAG = "FOVIX"

        private var tunInterface: ParcelFileDescriptor? = null

        private val running =
            AtomicBoolean(false)


        fun stopVPN() {

            Log.e(TAG,"========== STOP VPN ==========")

            running.set(false)

            tunInterface?.close()

            tunInterface=null
        }

    }




    override fun onCreate() {

        super.onCreate()

        Log.e(
            TAG,
            "========== VPN SERVICE CREATED =========="
        )

    }





    override fun onStartCommand(
        intent: Intent?,
        flags:Int,
        startId:Int
    ):Int {


        Log.e(
            TAG,
            "========== VPN START COMMAND =========="
        )


        if(running.get())
            return START_STICKY



        startVPN()


        return START_STICKY

    }






    private fun startVPN(){


        try {



            Log.e(
                TAG,
                "CREATE ANDROID TUN"
            )



            val builder =
                Builder()



            builder
                .setSession("FOVIX")
                .setMtu(1500)

                // VPN address
                .addAddress(
                    "10.0.0.2",
                    32
                )

                // ВСЁ через VPN
                .addRoute(
                    "0.0.0.0",
                    0
                )

                // IPv6 отключаем пока
                .addDnsServer(
                    "1.1.1.1"
                )



            tunInterface =
                builder.establish()



            if(tunInterface==null){

                Log.e(
                    TAG,
                    "TUN FAILED"
                )

                stopSelf()

                return
            }



            val fd =
                tunInterface!!
                    .detachFd()



            tunInterface=null



            Log.e(
                TAG,
                "TUN FD=$fd"
            )





            val server =
                VPNServer(

                    protocol="vless",

                    address="ai.noooo.win",

                    port=443,

                    uuid=
                    "c5c1c20f-691d-4850-988c-ee463f4799ad",

                    sni=
                    "cdn-v1-6a51ff3b.noooo.win",

                    fingerprint="chrome"

                )




            val config =
                SingBoxConfigBuilder
                    .build(server)



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
                    "ENGINE FAILED"
                )

                SingBoxNative.stop()

                stopSelf()

                return
            }



            running.set(true)



            Log.e(
                TAG,
                "========== FOVIX CONNECTED =========="
            )


        }
        catch(e:Exception){


            Log.e(
                TAG,
                "VPN ERROR",
                e
            )


            SingBoxNative.stop()

            stopSelf()

        }


    }






    override fun onDestroy(){


        Log.e(
            TAG,
            "VPN DESTROY"
        )


        SingBoxNative.stop()


        tunInterface?.close()


        running.set(false)


        super.onDestroy()

    }



}