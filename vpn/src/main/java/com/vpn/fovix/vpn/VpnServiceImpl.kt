package com.vpn.fovix.vpn


import android.content.Intent
import android.net.VpnService
import android.os.IBinder
import android.util.Log



class VpnServiceImpl : VpnService() {



    private var vpnInterface: Builder? = null



    override fun onCreate() {

        super.onCreate()


        Log.e(
            "FOVIX",
            "VPN SERVICE CREATED"
        )


    }





    override fun onStartCommand(

        intent: Intent?,

        flags: Int,

        startId: Int

    ): Int {


        Log.e(
            "FOVIX",
            "VPN SERVICE START COMMAND"
        )



        createTunnel()



        return START_STICKY

    }





    private fun createTunnel(){


        try {


            val builder = Builder()



            builder

                .setSession(
                    "FOVIX VPN"
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



            val interfaceResult =

                builder.establish()



            if(interfaceResult != null){


                vpnInterface = builder



                Log.e(
                    "FOVIX",
                    "TUN CREATED SUCCESS"
                )


            } else {


                Log.e(
                    "FOVIX",
                    "TUN CREATION FAILED"
                )


            }



        } catch(e: Exception){


            Log.e(

                "FOVIX",

                "TUN ERROR: ${e.message}"

            )


        }


    }





    override fun onDestroy() {


        Log.e(

            "FOVIX",

            "VPN SERVICE DESTROY"

        )


        vpnInterface = null


        super.onDestroy()


    }





    override fun onBind(intent: Intent?): IBinder? {


        return super.onBind(intent)


    }


}