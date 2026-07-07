package com.vpn.fovix.data.repository

import com.vpn.fovix.vpn.VpnEngine
import com.vpn.fovix.domain.vpnstate.VPNState


class VpnRepository(
    private val engine: VpnEngine
) {

    fun connect() {
        engine.connect()
    }


    fun disconnect() {
        engine.disconnect()
    }


    fun getState(): VPNState {

        return VPNState()

    }

}
