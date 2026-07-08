package com.vpn.fovix.vpn


import android.content.Context
import com.vpn.fovix.vpn.singbox.SingBoxBinaryManager
import com.vpn.fovix.vpn.singbox.SingBoxProcessManager



class VpnEngine(

    context: Context

) {



    private val binaryManager =

        SingBoxBinaryManager(context)



    private val singBox =

        SingBoxProcessManager()



    private var running = false




    fun connect(){


        if(running){

            return

        }



        val binary =

            binaryManager.getBinaryFile()



        singBox.start(

            binaryPath = binary.absolutePath,

            configPath = "config.json"

        )



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