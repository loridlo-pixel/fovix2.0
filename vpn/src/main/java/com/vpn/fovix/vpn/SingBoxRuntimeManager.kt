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
    "level": "debug"
  },
  "route": {
    "auto_detect_interface": true
  },
  "inbounds": [],
  "outbounds": [
    {
      "type": "direct",
      "tag": "direct",
      "override_address": "",
      "override_port": 0
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


            Log.d(
                "FOVIX",
                "CONFIG LENGTH = ${testConfig.length}"
            )


            val result =
                SingBoxNative.start(
                    testConfig
                )


            Log.d(
                "FOVIX",
                "SINGBOX JNI RESULT = $result"
            )


            val nativeRunning =
                SingBoxNative.isRunning()


            Log.d(
                "FOVIX",
                "SINGBOX NATIVE RUNNING = $nativeRunning"
            )


            running = result



            if(result){

                Log.d(
                    "FOVIX",
                    "SINGBOX RUNTIME STARTED"
                )

            }
            else{

                Log.e(
                    "FOVIX",
                    "SINGBOX RUNTIME FAILED"
                )

            }


        }
        catch(e: Exception){


            Log.e(
                "FOVIX",
                "SINGBOX ERROR ${e.message}"
            )


            running = false

        }

    }



    fun stop(){


        try {


            Log.d(
                "FOVIX",
                "STOP SINGBOX JNI"
            )


            val result =
                SingBoxNative.stop()



            Log.d(
                "FOVIX",
                "SINGBOX STOP RESULT = $result"
            )


        }
        catch(e: Exception){


            Log.e(
                "FOVIX",
                "SINGBOX STOP ERROR ${e.message}"
            )

        }



        running = false

    }



    fun isRunning(): Boolean {


        return try {


            SingBoxNative.isRunning()


        }
        catch(e: Exception){


            false

        }

    }


}