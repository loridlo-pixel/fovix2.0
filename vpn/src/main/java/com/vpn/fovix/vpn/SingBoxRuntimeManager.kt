package com.vpn.fovix.vpn

import android.content.Context
import android.util.Log


class SingBoxRuntimeManager(
    private val context: Context
) {


    private var running = false



    private val testConfig = """
    {
      "log": {
        "level": "info"
      },
      "inbounds": [],
      "outbounds": [
        {
          "type": "direct",
          "tag": "direct"
        }
      ]
    }
    """.trimIndent()



    fun start() {


        if (running) {

            Log.d(
                "FOVIX",
                "SINGBOX ALREADY RUNNING"
            )

            return
        }


        try {


            Log.d(
                "FOVIX",
                "START SINGBOX JNI"
            )


            val result =
                SingBoxNative.start(testConfig)



            Log.d(
                "FOVIX",
                "SINGBOX START RESULT=$result"
            )


            running = result



        } catch (e: Exception) {


            Log.e(
                "FOVIX",
                "SINGBOX START ERROR",
                e
            )


            running = false

        }

    }



    fun stop() {


        try {


            Log.d(
                "FOVIX",
                "STOP SINGBOX JNI"
            )


            val result =
                SingBoxNative.stop()


            Log.d(
                "FOVIX",
                "SINGBOX STOP RESULT=$result"
            )


        } catch (e: Exception) {


            Log.e(
                "FOVIX",
                "SINGBOX STOP ERROR",
                e
            )

        }


        running = false

    }



    fun isRunning(): Boolean {


        return try {

            SingBoxNative.isRunning()

        } catch (e: Exception) {

            false

        }

    }

}