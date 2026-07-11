package com.vpn.fovix.app

object LibboxLoader {

    private var loaded = false

    fun load() {
        if (!loaded) {
            System.loadLibrary("box")
            loaded = true
        }
    }
}