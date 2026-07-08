package com.vpn.fovix.app.presentation.home


data class HomeViewState(

    val connection: HomeConnectionState = HomeConnectionState(),

    val download:Int = 0,

    val upload:Int = 0

)
