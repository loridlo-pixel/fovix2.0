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


        Libbox.load(this)

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



        if(vpnInterface != null){


            Log.d(
                "FOVIX",
                "VPN ALREADY RUNNING"
            )


            return START_STICKY

        }



        createTunnel()



        return START_STICKY

    }








    private fun createTunnel(){


        try {


            val builder = Builder()



            builder
                .setSession("FOVIX VPN")
                .setMtu(1500)
                .addAddress(
                    "10.0.0.2",
                    32
                )
                .addRoute(
                    "0.0.0.0",
                    0
                )




            vpnInterface =
                builder.establish()




            if(vpnInterface == null){


                Log.e(
                    "FOVIX",
                    "TUN INTERFACE FAILED"
                )


                stopSelf()

                return

            }





            Log.d(
                "FOVIX",
                "TUN INTERFACE CREATED"
            )





            val config =
                SingBoxConfigProvider
                    .getConfig()





            Log.d(
                "FOVIX",
                "LIBBOX START REQUEST"
            )





            val result =
                Libbox.startBox(
                    config
                )





            Log.d(
                "FOVIX",
                "LIBBOX RESULT=$result"
            )



        }
        catch(e:Exception){


            Log.e(
                "FOVIX",
                "VPN CREATE ERROR",
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



        try {


            Libbox.stopBox()



        }
        catch(e:Exception){


            Log.e(
                "FOVIX",
                "LIBBOX STOP ERROR",
                e
            )

        }





        vpnInterface?.close()

        vpnInterface=null




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