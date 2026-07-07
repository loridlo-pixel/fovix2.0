package com.vpn.fovix.app.presentation.home

import com.vpn.fovix.core.decision.UserMode
import com.vpn.fovix.domain.vpnstate.ConnectionStatus


data class HomeUiState(

    val mode: UserMode = UserMode.SIMPLE,

    val title: String = "FOVIX",

    val status: ConnectionStatus = ConnectionStatus.DISCONNECTED,

    val serverName: String = "Auto",

    val ping: Int = 0,

    val speed: String = "0 Mbps",


    val showServerInfo: Boolean = false,

    val showMetrics: Boolean = false,

    val showExpertSettings: Boolean = false


) {

    val showServer: Boolean
        get() = showServerInfo


    val showExpert: Boolean
        get() = showExpertSettings

}