package com.vpn.fovix.vpn


import android.content.Context
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


        return binary


    }




    fun start(){


        if(process != null)
            return



        val binary = prepareBinary()



        process = ProcessBuilder(

            binary.absolutePath,

            "version"

        )

            .redirectErrorStream(true)

            .start()



    }




    fun stop(){


        process?.destroy()

        process = null


    }




    fun isRunning(): Boolean{


        return process != null


    }


}