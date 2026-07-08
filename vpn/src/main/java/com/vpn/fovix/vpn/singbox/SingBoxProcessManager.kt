package com.vpn.fovix.vpn.singbox


class SingBoxProcessManager {


    private var process: Process? = null



    fun start(

        configPath: String

    ) {


        if(process != null){

            return

        }


        /*
            STEP 38

            Здесь позже будет:

            assets/singbox/sing-box
            -c config.json

        */


        process = null

    }




    fun stop(){


        process?.destroy()

        process = null


    }




    fun isRunning(): Boolean {


        return process != null

    }


}