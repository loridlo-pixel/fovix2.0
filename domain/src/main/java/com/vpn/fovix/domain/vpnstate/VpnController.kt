package com.vpn.fovix.domain.vpnstate

import kotlinx.coroutines.flow.StateFlow


interface VpnController {


    val state: StateFlow<VPNState>


    fun start(
        server: Any? = null
    )


    fun stop()


}