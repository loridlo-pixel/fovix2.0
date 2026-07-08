package com.vpn.fovix.app.presentation.home

import com.vpn.fovix.domain.vpnstate.VPNState


fun VPNState.toHomeConnectionState(): HomeConnectionState {

    return HomeConnectionState(

        status = status,

        server = server ?: "AUTO",

        ping = latency?.toInt() ?: 0

    )

}