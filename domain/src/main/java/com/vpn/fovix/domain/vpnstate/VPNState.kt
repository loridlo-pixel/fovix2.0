package com.vpn.fovix.domain.vpnstate


data class VPNState(

    val status: ConnectionStatus = ConnectionStatus.DISCONNECTED,

    val server: String = "Auto",

    val download: Int = 0,

    val upload: Int = 0

)