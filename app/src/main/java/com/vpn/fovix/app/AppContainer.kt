package com.vpn.fovix.app


import com.vpn.fovix.core.decision.DecisionEngine
import com.vpn.fovix.data.repository.VpnRepository
import com.vpn.fovix.vpn.VpnEngine


class AppContainer {


    private val vpnEngine = VpnEngine()


    private val decisionEngine = DecisionEngine()



    val vpnRepository: VpnRepository =

        VpnRepository(

            vpnEngine = vpnEngine,

            decisionEngine = decisionEngine

        )


}