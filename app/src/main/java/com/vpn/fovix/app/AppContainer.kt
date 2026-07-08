package com.vpn.fovix.app


import android.content.Context
import com.vpn.fovix.core.decision.DecisionEngine
import com.vpn.fovix.data.repository.VpnRepository
import com.vpn.fovix.vpn.SingBoxRuntimeManager
import com.vpn.fovix.vpn.VpnEngine


class AppContainer(

    context: Context

) {


    private val runtimeManager =

        SingBoxRuntimeManager(
            context
        )



    private val vpnEngine =

        VpnEngine(
            runtimeManager
        )



    private val decisionEngine =

        DecisionEngine()



    val vpnRepository: VpnRepository =

        VpnRepository(

            vpnEngine = vpnEngine,

            decisionEngine = decisionEngine

        )


}