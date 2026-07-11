package com.vpn.fovix.vpn

import android.content.Context
import android.util.Log


class SingboxRunner(
    private val context: Context
) {


    fun start(
        config: String,
        fd: Int
    ) {


        Log.d(
            "FOVIX",
            "LIBBOX START REQUEST FD=$fd"
        )


        try {


            if (!Libbox.isLoaded()) {

                Libbox.load(
                    context
                )

            }



            val result = Libbox.startBox(
                config
            )



            if(result){


                Log.d(
                    "FOVIX",
                    "SINGBOX STARTED"
                )


            } else {


                Log.e(
                    "FOVIX",
                    "SINGBOX START FAILED"
                )

            }



        } catch(e: Exception){


            Log.e(
                "FOVIX",
                "LIBBOX START ERROR",
                e
            )

        }


    }




    fun stop(){


        Log.d(
            "FOVIX",
            "LIBBOX STOP REQUEST"
        )


        try {


            Libbox.stopBox()



            Log.d(
                "FOVIX",
                "SINGBOX STOPPED"
            )


        }
        catch(e: Exception){


            Log.e(
                "FOVIX",
                "LIBBOX STOP ERROR",
                e
            )

        }


    }


}