package com.vpn.fovix.vpn


class VpnEngine {


    private var running = false


    fun connect() {

        start()

    }


    fun disconnect() {

        stop()

    }


    fun start() {

        if (running)
            return


        running = true

        // TODO:
        // start sing-box process here

    }


    fun stop() {

        running = false

        // TODO:
        // stop sing-box process

    }


    fun isRunning(): Boolean {

        return running

    }

}