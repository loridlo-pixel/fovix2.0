package com.vpn.fovix.vpn


import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



class FovixVpnService : VpnService() {



    companion object {


        private const val TAG = "FOVIX_SERVICE"



        private const val MTU = 1500



        private const val VPN_ADDRESS = "10.0.0.2"

        private const val VPN_PREFIX = 32



        private const val DNS_PRIMARY = "1.1.1.1"

        private const val DNS_SECONDARY = "8.8.8.8"


    }





    private var tunInterface: ParcelFileDescriptor? = null



    private var worker: Job? = null







    override fun onCreate() {


        super.onCreate()



        Log.i(
            TAG,
            "SERVICE CREATED"
        )


    }









    override fun onStartCommand(

        intent: Intent?,
        flags: Int,
        startId: Int

    ): Int {



        Log.i(
            TAG,
            "SERVICE START"
        )



        startVpn()



        return START_STICKY

    }










    private fun startVpn(){



        if(tunInterface != null){


            Log.i(
                TAG,
                "VPN ALREADY RUNNING"
            )


            return

        }





        try {



            val builder = Builder()



            builder
                .setSession("FOVIX VPN")
                .setMtu(MTU)
                .addAddress(
                    VPN_ADDRESS,
                    VPN_PREFIX
                )





            /*
             DNS через VPN
             */
            builder
                .addDnsServer(
                    DNS_PRIMARY
                )

                .addDnsServer(
                    DNS_SECONDARY
                )





            /*
             Весь IPv4 трафик
             */
            builder
                .addRoute(
                    "0.0.0.0",
                    0
                )





            /*
             IPv6
             */
            builder
                .addRoute(
                    "::",
                    0
                )





            tunInterface =
                builder.establish()





            if(tunInterface == null){


                Log.e(
                    TAG,
                    "TUN CREATE FAILED"
                )


                stopSelf()

                return

            }







            val fd =
                tunInterface!!
                    .fd





            Log.i(
                TAG,
                "TUN FD=$fd"
            )






            startSingBox(fd)







        }
        catch(e: Exception){



            Log.e(
                TAG,
                "VPN START ERROR",
                e
            )


            stopSelf()


        }



    }









    private fun startSingBox(

        tunFd:Int

    ){





        worker =
            CoroutineScope(
                Dispatchers.IO
            )
            .launch {



                try {



                    val config =
                        buildConfig()





                    Log.i(
                        TAG,
                        "CONFIG SIZE=${config.length}"
                    )







                    val result =
                        SingBoxNative.start(

                            config,

                            tunFd

                        )







                    Log.i(
                        TAG,
                        "SINGBOX START RESULT=$result"
                    )







                    if(result){


                        Log.i(
                            TAG,
                            "FOVIX CONNECTED"
                        )

                    }





                }
                catch(e:Exception){


                    Log.e(
                        TAG,
                        "SINGBOX ERROR",
                        e
                    )


                }



            }



    }









    private fun buildConfig():String {



        return """
        {
          "log": {
            "level": "debug"
          },

          "inbounds": [
            {
              "type": "tun",
              "tag": "fovix-tun",
              "interface_name": "tun0",
              "address": [
                "10.0.0.2/32"
              ],
              "auto_route": false,
              "strict_route": false,
              "stack": "gvisor"
            }
          ],


          "outbounds": [
            {
              "type": "direct",
              "tag": "direct"
            }
          ]
        }
        """.trimIndent()



    }









    override fun onDestroy(){



        Log.i(
            TAG,
            "SERVICE DESTROY"
        )



        worker?.cancel()



        try {


            SingBoxNative.stop()



        }
        catch(_:Exception){}





        tunInterface?.close()



        tunInterface=null



        super.onDestroy()



    }




}