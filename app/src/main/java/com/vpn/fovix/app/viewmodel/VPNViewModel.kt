package com.vpn.fovix.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vpn.fovix.domain.vpnstate.ConnectionStatus
import com.vpn.fovix.domain.vpnstate.VPNState
import com.vpn.fovix.vpn.VpnEngine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class VPNViewModel(
    private val vpnEngine: VpnEngine = VpnEngine()
) : ViewModel() {


    private val _state =
        MutableStateFlow(
            VPNState()
        )


    val state: StateFlow<VPNState> =
        _state



    fun connect(){

        viewModelScope.launch {

            _state.value =
                _state.value.copy(
                    status = ConnectionStatus.CONNECTING
                )


            vpnEngine.start()


            _state.value =
                _state.value.copy(
                    status = ConnectionStatus.CONNECTED,
                    protectionEnabled = true
                )

        }

    }



    fun disconnect(){

        viewModelScope.launch {


            vpnEngine.stop()


            _state.value =
                VPNState(
                    status = ConnectionStatus.DISCONNECTED
                )

        }

    }


}
