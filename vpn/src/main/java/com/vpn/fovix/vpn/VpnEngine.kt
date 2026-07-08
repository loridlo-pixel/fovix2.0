package com.vpn.fovix.vpn


import com.vpn.fovix.vpn.singbox.SingBoxProcessManager



class VpnEngine {



    private val singBox =

        SingBoxProcessManager()



    private var running = false





    fun connect(){


        if(running){

            return

        }


        /*
            STEP 39

            Реальный запуск будет после передачи:

            binaryPath
            configPath

        */


        running = true


    }





    fun disconnect(){


        singBox.stop()


        running = false


    }





    fun isRunning(): Boolean {


        return running

    }


}