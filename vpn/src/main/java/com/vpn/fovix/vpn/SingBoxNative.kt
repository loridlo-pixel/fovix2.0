package com.vpn.fovix.vpn


object SingBoxNative {


    init {

        System.loadLibrary("fovix")

    }


    external fun start(
        config: String,
        tunFd: Int
    ): Boolean



    external fun stop(): Boolean



    external fun isRunning(): Boolean

}