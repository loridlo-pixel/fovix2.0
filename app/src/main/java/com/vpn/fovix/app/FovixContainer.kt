package com.vpn.fovix.app


import android.content.Context
import com.vpn.fovix.core.decision.DecisionEngine
import com.vpn.fovix.data.repository.VpnRepository
import com.vpn.fovix.vpn.VpnEngine


object FovixContainer {


    lateinit var repository: VpnRepository
        private set



    fun init(context: Context) {


        val vpnEngine = VpnEngine(
            context.applicationContext
        )


        val decisionEngine = DecisionEngine()



        repository =
            VpnRepository(
                vpnEngine,
                decisionEngine
            )

    }

}