package com.vpn.fovix.data.repository


import com.vpn.fovix.vpn.VpnEngine
import com.vpn.fovix.domain.vpnstate.VPNState
import com.vpn.fovix.domain.vpnstate.ConnectionStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class VpnRepository(
    private val engine: VpnEngine
){


private val _state =
    MutableStateFlow(
        VPNState(
            status = ConnectionStatus.DISCONNECTED
        )
    )


val state: StateFlow<VPNState>
    get() = _state



fun connect(){

    _state.value =
        VPNState(
            status = ConnectionStatus.CONNECTING
        )


    engine.start()


    _state.value =
        VPNState(
            status = ConnectionStatus.CONNECTED
        )

}



fun disconnect(){

    engine.stop()


    _state.value =
        VPNState(
            status = ConnectionStatus.DISCONNECTED
        )

}


}

