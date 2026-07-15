package com.vpn.fovix.data.repository


import android.util.Log
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





    fun startVpn(

        mode: UserMode = UserMode.SIMPLE

    ) {


        Log.e(
            "FOVIX",
            "START VPN AFTER PERMISSION"
        )


        val decision =
            decisionEngine.evaluate(mode)



        vpnEngine.startService()



        _state.value =
            _state.value.copy(

                status = ConnectionStatus.CONNECTING,

                server = decision.recommendedServer

            )


    }





    fun disconnect(){


        Log.e(
            "FOVIX",
            "DISCONNECT"
        )


        vpnEngine.stopService()



        _state.value =
            _state.value.copy(

                status = ConnectionStatus.DISCONNECTED

            )

    }





    fun toggle(){


        if(
            _state.value.status ==
            ConnectionStatus.CONNECTED
        ){

            disconnect()

        }
        else {

            startVpn()

        }


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