package com.vpn.fovix.vpn


class VpnEngine {


    private var running = false



    fun connect(){


        start()


    }




    fun disconnect(){


        stop()


    }




    fun start(){


        if(running){

            return

        }


        running = true


        // TODO:
        // sing-box запуск добавим позже

    }





    fun stop(){


        running = false


        // TODO:
        // sing-box остановка позже

    }





    fun isRunning(): Boolean {


        return running

    }


}