package com.vpn.fovix.domain.vpnstate


data class VPNState(

    val status: ConnectionStatus = ConnectionStatus.DISCONNECTED,

    val server: String? = null,

    val ip: String? = null,

    val latency: Long? = null,

    val protectionEnabled: Boolean = false

)
