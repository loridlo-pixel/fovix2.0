package com.vpn.fovix.domain.vpnprofile


data class VpnProfile(

    val name: String,

    val country: String,

    val server: String,

    val port: Int,

    val uuid: String,

    val sni: String,

    val fingerprint: String = "chrome"

)