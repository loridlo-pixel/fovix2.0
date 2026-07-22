package com.vpn.fovix.vpn

import android.util.Log


object SingBoxRuntimeManager {


    private const val TAG = "FOVIX_RUNTIME"


    private var running = false



    fun start(
        config: String,
        tunFd: Int
    ): Boolean {


        Log.e(
            TAG,
            "========== START =========="
        )


        Log.e(
            TAG,
            "CONFIG SIZE=${config.length}"
        )


        Log.e(
            TAG,
            "TUN FD=$tunFd"
        )



        if (tunFd <= 0) {

            Log.e(
                TAG,
                "INVALID TUN FD"
            )

            return false
        }



        if (running) {

            Log.e(
                TAG,
                "ALREADY RUNNING"
            )

            return true
        }



        val result =
            try {

                SingBoxNative.start(
                    config,
                    tunFd
                )

            }
            catch(e: Exception) {

                Log.e(
                    TAG,
                    "NATIVE ERROR",
                    e
                )

                false
            }




        Log.e(
            TAG,
            "NATIVE RESULT=$result"
        )



        running = result


        return result

    }




    fun stop(): Boolean {


        Log.e(
            TAG,
            "STOP"
        )


        val result =
            SingBoxNative.stop()


        running = false


        return result

    }




    fun isRunning(): Boolean {


        return try {

            SingBoxNative.isRunning()

        }
        catch(e: Exception) {

            false

        }

    }

}