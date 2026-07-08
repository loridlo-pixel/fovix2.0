package com.vpn.fovix.app

import android.app.Application

class FovixApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = AppContainer()
    }
}