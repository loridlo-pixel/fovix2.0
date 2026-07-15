package com.vpn.fovix.app.presentation.home


import com.vpn.fovix.domain.vpnstate.VPNState



fun VPNState.toHomeUiState(): HomeUiState {


    return HomeUiState(

        status = status,

        connected =
            status.name == "CONNECTED",

        server = server,

        download = download,

        upload = upload

    )

}
