package com.vpn.fovix.vpn


class VpnEngine(

    private val runtimeManager: SingBoxRuntimeManager? = null

) {


    private var running = false



    fun connect(){


        if(running)
            return


        running = true


        println(
            "FOVIX VPN ENGINE STARTED"
        )


    }



    fun disconnect(){


        if(!running)
            return


        runtimeManager?.stop()


        running = false


        println(
            "FOVIX VPN ENGINE STOPPED"
        )


    }



    fun isRunning(): Boolean {


        return running


    }


}