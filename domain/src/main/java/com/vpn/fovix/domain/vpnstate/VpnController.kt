package com.vpn.fovix.domain.vpnstate


import com.vpn.fovix.domain.vpnprofile.VpnProfile

import kotlinx.coroutines.flow.StateFlow



interface VpnController {


    val state: StateFlow<VPNState>



    fun start(
        profile: VpnProfile
    )



    fun stop()


}