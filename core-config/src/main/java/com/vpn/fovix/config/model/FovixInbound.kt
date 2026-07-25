package com.vpn.fovix.config.model


data class FovixInbound(

    val type: String = "tun",

    val tag: String = "tun-in",

    val mtu: Int = 1500,

    val autoRoute: Boolean = true,

    val strictRoute: Boolean = true,

    val stack: String = "system"

)