package com.vpn.fovix.vpn


class VpnEngine(

    private val runtimeManager: SingBoxRuntimeManager

) {


    private var running = false



    fun connect(){


        if(running)
            return



        try {


            runtimeManager.start()



            running = true



            println(
                "FOVIX VPN ENGINE STARTED"
            )


        } catch (e: Exception){


            println(
                "FOVIX START ERROR: ${e.message}"
            )


        }


    }



    fun disconnect(){


        if(!running)
            return



        runtimeManager.stop()



        running = false



        println(
            "FOVIX VPN ENGINE STOPPED"
        )


    }



    fun isRunning(): Boolean {


        return running


    }


}