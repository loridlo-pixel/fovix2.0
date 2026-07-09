package com.vpn.fovix.vpn


object SingBoxNative {


    init {
        System.loadLibrary("singbox")
    }


    external fun start(config: String): Boolean


    external fun stop(): Boolean


    external fun isRunning(): Boolean


}