package com.vpn.fovix.app.presentation.home


import com.vpn.fovix.domain.usermode.UserMode
import com.vpn.fovix.domain.vpnstate.ConnectionStatus



data class HomeUiState(

    val status: ConnectionStatus,

    val mode: UserMode,

    val showServer:Boolean,

    val showMetrics:Boolean,

    val showExpert:Boolean

)
