package com.vpn.fovix.data.repository


import com.vpn.fovix.core.decision.DecisionEngine
import com.vpn.fovix.core.decision.UserMode
import com.vpn.fovix.domain.vpnstate.ConnectionStatus
import com.vpn.fovix.domain.vpnstate.VPNState
import com.vpn.fovix.vpn.VpnEngine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow



class VpnRepository(

    private val vpnEngine: VpnEngine,

    private val decisionEngine: DecisionEngine

) {


    private val _state =

        MutableStateFlow(
            VPNState()
        )


    val state: StateFlow<VPNState> =

        _state



    fun connect(

        mode: UserMode = UserMode.SIMPLE

    ) {


        val decision =

            decisionEngine.evaluate(mode)



        vpnEngine.connect()



        _state.value =

            _state.value.copy(

                status = ConnectionStatus.CONNECTED,

                server = decision.recommendedServer,

                protectionEnabled = true

            )


    }




    fun disconnect(){


        vpnEngine.disconnect()



        _state.value =

            _state.value.copy(

                status = ConnectionStatus.DISCONNECTED,

                protectionEnabled = false

            )


    }




    fun chooseServer(

        mode: UserMode

    ){


        val decision =

            decisionEngine.evaluate(mode)



        _state.value =

            _state.value.copy(

                server = decision.recommendedServer

            )


    }



}