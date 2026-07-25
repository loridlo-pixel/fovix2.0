package com.vpn.fovix.config.model


data class FovixInbound(

    val type: String = "tun",

    val tag: String = "tun-in",

    val mtu: Int = 1500,

    val address: String = "172.19.0.1/30",

    val autoRoute: Boolean = true,

    val strictRoute: Boolean = false,

    val stack: String = "gvisor"

)