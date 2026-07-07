package com.vpn.fovix.domain.vpnstate


sealed class VPNAction {

    object Connect : VPNAction()

    object Disconnect : VPNAction()

}

