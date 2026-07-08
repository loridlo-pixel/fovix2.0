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


        Log.d(
            "FOVIX",
            "Repository connect called"
        )



        val decision =

            decisionEngine.evaluate(mode)



        Log.d(
            "FOVIX",
            "Selected server: ${decision.recommendedServer}"
        )



        vpnEngine.connect()



        _state.value =

            _state.value.copy(

                status = ConnectionStatus.CONNECTED,

                server = decision.recommendedServer

            )



        Log.d(
            "FOVIX",
            "State changed CONNECTED"
        )


    }





    fun disconnect(){


        Log.d(
            "FOVIX",
            "Repository disconnect called"
        )



        vpnEngine.disconnect()



        _state.value =

            _state.value.copy(

                status = ConnectionStatus.DISCONNECTED

            )



        Log.d(
            "FOVIX",
            "State changed DISCONNECTED"
        )


    }





    fun toggle(){


        val current = _state.value.status



        Log.d(
            "FOVIX",
            "Toggle current state: $current"
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