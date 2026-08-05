package com.vpn.fovix.domain.vpnserver


data class VpnServer(

    val id: String,

    val name: String,

    val country: String,

    val flag: String,


    val host: String,

    val port: Int,


    val uuid: String,

    val sni: String,


    val protocol: String = "vless",


    val latency: Int = 0,


    val premiumOnly: Boolean = false

)