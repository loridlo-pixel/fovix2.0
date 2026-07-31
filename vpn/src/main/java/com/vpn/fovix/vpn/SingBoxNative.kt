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



    /**
     * Возвращает текущее состояние FOVIX Core
     *
     * Возможные значения:
     * CHECKING
     * CONNECTED
     * ERROR
     * DISCONNECTED
     */
    external fun getState(): String


}