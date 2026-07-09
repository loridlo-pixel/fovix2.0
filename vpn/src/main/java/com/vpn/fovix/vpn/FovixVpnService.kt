package com.vpn.fovix.vpn


import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor
import android.util.Log


class FovixVpnService : VpnService() {


    private var vpnInterface: ParcelFileDescriptor? = null


    private lateinit var singBoxRuntimeManager: SingBoxRuntimeManager



    override fun onCreate() {

        super.onCreate()


        Log.d(
            "FOVIX",
            "VPN SERVICE CREATED"
        )


        singBoxRuntimeManager =

            SingBoxRuntimeManager(
                this
            )

    }




    override fun onStartCommand(

        intent: Intent?,

        flags: Int,

        startId: Int

    ): Int {


        Log.d(
            "FOVIX",
            "VPN SERVICE START COMMAND"
        )


        startVpn()


        return START_STICKY

    }





    private fun startVpn(){


        if(vpnInterface != null){

            Log.d(
                "FOVIX",
                "VPN ALREADY RUNNING"
            )

            return

        }



        val builder = Builder()



        builder.setSession(
            "FOVIX"
        )


        builder.addAddress(
            "10.0.0.2",
            32
        )


        builder.addRoute(
            "0.0.0.0",
            0
        )



        vpnInterface =

            builder.establish()



        if(vpnInterface != null){


            Log.d(
                "FOVIX",
                "TUN INTERFACE CREATED"
            )



            try {


                singBoxRuntimeManager.start()



            } catch(e: Exception){


                Log.e(
                    "FOVIX",
                    "SINGBOX START FAILED ${e.message}"
                )


            }



        }
        else{


            Log.e(
                "FOVIX",
                "TUN CREATION FAILED"
            )


        }


    }





    override fun onDestroy(){


        Log.d(
            "FOVIX",
            "VPN SERVICE DESTROY"
        )



        singBoxRuntimeManager.stop()



        vpnInterface?.close()


        vpnInterface = null



        super.onDestroy()

    }


}