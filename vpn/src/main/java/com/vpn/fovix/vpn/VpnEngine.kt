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



        singBox.start(

            configPath = "config.json"

        )



        running = true


    }





    fun disconnect(){


        if(!running){

            return

        }



        singBox.stop()



        running = false


    }





    fun isRunning(): Boolean {


        return running

    }


}