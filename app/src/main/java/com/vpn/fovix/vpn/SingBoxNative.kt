package com.vpn.fovix.vpn


object SingBoxNative {


    init {
        System.loadLibrary("fovix")
    }



    /**
     * Запуск FOVIX engine
     *
     * @param config JSON конфигурация sing-box
     * @param tunFd Android VPNService TUN file descriptor
     */
    external fun start(
        config: String,
        tunFd: Int
    ): Boolean



    /**
     * Остановка FOVIX engine
     */
    external fun stop(): Boolean



    /**
     * Проверка состояния FOVIX engine
     */
    external fun isRunning(): Boolean

}