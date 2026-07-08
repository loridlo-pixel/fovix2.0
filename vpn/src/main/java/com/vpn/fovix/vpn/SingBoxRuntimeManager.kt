package com.vpn.fovix.vpn


import android.content.Context
import android.util.Log
import java.io.File


class SingBoxRuntimeManager(

    private val context: Context

){


    private var process: Process? = null



    private fun prepareBinary(): File {


        val binary = File(

            context.filesDir,

            "sing-box"

        )



        if(!binary.exists()){


            Log.d(
                "FOVIX",
                "Copy sing-box binary"
            )


            context.assets
                .open("singbox/sing-box")
                .use { input ->


                    binary.outputStream()
                        .use { output ->

                            input.copyTo(output)

                        }


                }


            binary.setExecutable(true)


        }



        Log.d(
            "FOVIX",
            "Binary path: ${binary.absolutePath}"
        )


        return binary

    }




    fun start(){


        if(process != null){

            Log.d(
                "FOVIX",
                "Already running"
            )

            return

        }



        val binary = prepareBinary()



        process = ProcessBuilder(

            binary.absolutePath,

            "version"

        )

            .redirectErrorStream(true)

            .start()



        Log.d(
            "FOVIX",
            "SingBox started"
        )


    }




    fun stop(){


        Log.d(
            "FOVIX",
            "SingBox stopped"
        )


        process?.destroy()


        process = null


    }



    fun isRunning(): Boolean {


        return process != null


    }


}