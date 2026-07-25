package com.vpn.fovix.config.model


data class FovixRoute(

    val autoDetectInterface: Boolean = true,

    val finalOutbound: String = "proxy"

)