package com.vpn.fovix.app.presentation.home


import com.vpn.fovix.domain.vpnstate.ConnectionStatus



data class HomeUiState(


    val status: ConnectionStatus =

        ConnectionStatus.DISCONNECTED,



    val connected: Boolean = false,



    val server: String = "Auto",



    val download: Int = 0,



    val upload: Int = 0,



    val userMode: UserMode = UserMode.SIMPLE,



    val showServer: Boolean = false,



    val showMetrics: Boolean = false,



    val showExpert: Boolean = false



)