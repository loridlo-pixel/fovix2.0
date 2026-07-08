package com.vpn.fovix.vpn.singbox



class SingBoxProcessManager {



    private var process: Process? = null



    fun start(

        binaryPath: String,

        configPath: String

    ){



        if(process != null){

            return

        }



        val command = listOf(

            binaryPath,

            "run",

            "-c",

            configPath

        )



        process =

            ProcessBuilder(command)

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