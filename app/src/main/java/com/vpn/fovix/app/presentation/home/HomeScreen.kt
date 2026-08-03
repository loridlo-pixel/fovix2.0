package com.vpn.fovix.app.presentation.home


import androidx.compose.runtime.Composable

import com.vpn.fovix.domain.vpnstate.ConnectionStatus



@Composable
fun HomeScreen() {


    HomeDashboard(

        status = ConnectionStatus.DISCONNECTED,

        server = "Auto",

        download = 0,

        upload = 0,

        mode = UserMode.SIMPLE,

        onModeChange = {},

        onConnectClick = {}

    )


}