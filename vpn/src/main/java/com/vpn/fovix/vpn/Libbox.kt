package com.vpn.fovix.vpn

import android.content.Context
import android.util.Log


object Libbox {


    private var loaded = false



    fun load(
        context: Context
    ) {


        if(loaded)
            return


        try {


            System.loadLibrary("singbox")


            loaded = true


            Log.d(
                "FOVIX",
                "libbox.so loaded"
            )


        }
        catch(e: Throwable){


            Log.e(
                "FOVIX",
                "libbox load failed",
                e
            )


        }


    }





    fun isLoaded():Boolean {


        return loaded

    }





    fun startBox(
        config:String
    ):Boolean {


        return try {


            SingBoxNative.start(
                config
            )


        }
        catch(e:Exception){


            Log.e(
                "FOVIX",
                "startBox error",
                e
            )


            false

        }


    }






    fun stopBox(){


        try {


            SingBoxNative.stop()


        }
        catch(e:Exception){


            Log.e(
                "FOVIX",
                "stopBox error",
                e
            )

        }


    }


}