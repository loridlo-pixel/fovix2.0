package com.vpn.fovix.vpn


import android.util.Log


object SingBoxNative {


    init {

        try {

            System.loadLibrary("singbox")

            Log.d(
                "FOVIX",
                "JNI LIBRARY LOADED"
            )


        } catch (e: Throwable) {


            Log.e(
                "FOVIX",
                "JNI LOAD FAILED ${e.message}",
                e
            )


        }

    }



    external fun start(config: String): Boolean


    external fun stop(): Boolean


    external fun isRunning(): Boolean



}