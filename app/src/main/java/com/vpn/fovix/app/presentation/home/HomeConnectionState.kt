package com.vpn.fovix.app.presentation.home

import com.vpn.fovix.domain.vpnstate.ConnectionStatus


data class HomeConnectionState(

    val status: ConnectionStatus = ConnectionStatus.DISCONNECTED,

    val server: String = "AUTO",

    val ping: Int = 0

)
