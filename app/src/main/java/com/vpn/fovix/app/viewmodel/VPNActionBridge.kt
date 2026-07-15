package com.vpn.fovix.app.viewmodel

import com.vpn.fovix.domain.vpnstate.VPNAction

class VPNActionBridge(
    private val viewModel: VPNViewModel
) {


    fun connect() {

        viewModel.handleAction(
            VPNAction.Connect
        )

    }


    fun disconnect() {

        viewModel.handleAction(
            VPNAction.Disconnect
        )

    }

}
