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





    fun connect(

        mode: UserMode = UserMode.SIMPLE

    ) {


        Log.e(
            "FOVIX",
            "CONNECT START"
        )


        val decision =

            decisionEngine.evaluate(mode)



        Log.e(
            "FOVIX",
            "SERVER = ${decision.recommendedServer}"
        )



        vpnEngine.connect()



        _state.value =

            _state.value.copy(

                status = ConnectionStatus.CONNECTED,

                server = decision.recommendedServer

            )



        Log.e(
            "FOVIX",
            "STATUS CONNECTED"
        )


    }





    fun disconnect(){


        Log.e(
            "FOVIX",
            "DISCONNECT START"
        )



        vpnEngine.disconnect()



        _state.value =

            _state.value.copy(

                status = ConnectionStatus.DISCONNECTED

            )



        Log.e(
            "FOVIX",
            "STATUS DISCONNECTED"
        )


    }





    fun toggle(){


        val current =

            _state.value.status



        Log.e(
            "FOVIX",
            "TOGGLE $current"
        )



        if(

            current == ConnectionStatus.CONNECTED

        ){


            disconnect()


        } else {


            connect()


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