package com.vpn.fovix.vpn

import android.util.Log


object SingBoxRuntimeManager {


    private const val TAG = "FOVIX"


    private var running = false



    fun start(
        config: String,
        tunFd: Int
    ): Boolean {


        if (running) {

            Log.d(
                TAG,
                "FOVIX ALREADY RUNNING"
            )

            return true

        }



        Log.d(
            TAG,
            "FOVIX RUNTIME START"
        )



        Log.d(
            TAG,
            "TUN FD=$tunFd"
        )



        val result =
            SingBoxNative.start(
                config,
                tunFd
            )



        Log.d(
            TAG,
            "FOVIX NATIVE START RESULT=$result"
        )



        running = result



        return result

    }




    fun stop(): Boolean {


        Log.d(
            TAG,
            "FOVIX RUNTIME STOP"
        )



        val result =
            SingBoxNative.stop()



        Log.d(
            TAG,
            "FOVIX NATIVE STOP RESULT=$result"
        )



        running = false



        return result

    }





    fun isRunning(): Boolean {


        return try {


            SingBoxNative.isRunning()


        }
        catch (e: Exception) {


            Log.e(
                TAG,
                "FOVIX CHECK ERROR",
                e
            )


            false

        }

    }


}