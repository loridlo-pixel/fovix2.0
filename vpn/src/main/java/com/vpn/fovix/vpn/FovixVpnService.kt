package com.vpn.fovix.vpn


import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor
import android.util.Log


class FovixVpnService : VpnService() {


    private var vpnInterface: ParcelFileDescriptor? = null



    override fun onCreate() {

        super.onCreate()

        Log.d(
            "FOVIX",
            "VPN SERVICE CREATED"
        )

    }




    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {


        Log.d(
            "FOVIX",
            "VPN START COMMAND"
        )



        if(vpnInterface != null){

            Log.d(
                "FOVIX",
                "VPN ALREADY RUNNING"
            )

            return START_STICKY

        }



        startVpn()


        return START_STICKY

    }





    private fun startVpn(){


        try {


            val builder = Builder()


            builder

                .setSession(
                    "FOVIX VPN"
                )

                .setMtu(
                    1500
                )

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



            vpnInterface =
                builder.establish()



            if(vpnInterface == null){


                Log.e(
                    "FOVIX",
                    "TUN CREATE FAILED"
                )


                stopSelf()

                return

            }



            Log.d(
                "FOVIX",
                "TUN CREATED"
            )





            val server = VPNServer(

                protocol = "vless",

                address = "ai.noooo.win",

                port = 443,

                uuid = "c5c1c20f-691d-4850-988c-ee463f4799ad",

                sni = "cdn-v1-6a51ff3b.noooo.win",

                fingerprint = "firefox",


                options = mapOf(

                    "flow" to ""

                )

            )





            val config =
                SingBoxConfigBuilder.build(server)



            Log.d(
                "FOVIX",
                "CONFIG SIZE=${config.length}"
            )



            Log.d(
                "FOVIX",
                config
            )



            val started =
                SingBoxNative.start(
                    config
                )



            Log.d(
                "FOVIX",
                "SINGBOX START RESULT=$started"
            )



            if(started){


                Log.d(
                    "FOVIX",
                    "FOVIX CORE RUNNING"
                )


            }
            else{


                Log.e(
                    "FOVIX",
                    "FOVIX CORE FAILED"
                )


            }



        }
        catch(e:Exception){


            Log.e(
                "FOVIX",
                "VPN START ERROR",
                e
            )


            stopSelf()

        }


    }





    override fun onDestroy(){


        Log.d(
            "FOVIX",
            "VPN SERVICE DESTROY"
        )


        try{


            SingBoxNative.stop()


        }
        catch(e:Exception){


            Log.e(
                "FOVIX",
                "SINGBOX STOP ERROR",
                e
            )

        }



        vpnInterface?.close()

        vpnInterface = null



        super.onDestroy()

    }




    override fun onRevoke(){


        Log.d(
            "FOVIX",
            "VPN PERMISSION REVOKED"
        )


        stopSelf()


        super.onRevoke()

    }


}