package com.vpn.fovix.core.di

import com.vpn.fovix.vpn.VpnEngine
import com.vpn.fovix.data.repository.VpnRepository


class AppContainer {


    private val vpnEngine = VpnEngine()


    val vpnRepository =
        VpnRepository(vpnEngine)

}
