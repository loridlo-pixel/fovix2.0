package com.vpn.fovix.vpn

import android.content.Context
import java.io.File


class SingBoxRuntimeManager(

    private val context: Context

) {


    private var process: Process? = null



    fun prepareBinary(): File {


        val target = File(

            context.filesDir,

            "sing-box"

        )


        if (!target.exists()) {


            context.assets
                .open("singbox/sing-box")
                .use { input ->


                    target.outputStream()
                        .use { output ->

                            input.copyTo(output)

                        }


                }


            target.setExecutable(true)

        }


        return target

    }



    fun start(

        configPath: String

    ) {


        if (process != null)
            return



        val binary = prepareBinary()



        process = ProcessBuilder(

            binary.absolutePath,

            "run",

            "-c",

            configPath

        )

            .redirectErrorStream(true)

            .start()


    }



    fun stop(){


        process?.destroy()

        process = null


    }



    fun isRunning(): Boolean {


        return process != null


    }


}