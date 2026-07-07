package com.vpn.fovix.domain.vpnstate


data class ConnectionInfo(

    val serverName: String,

    val country: String,

    val ping: Long

)
