package com.vpn.fovix.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vpn.fovix.data.repository.VpnRepository
import com.vpn.fovix.domain.vpnstate.ConnectionStatus
import com.vpn.fovix.domain.vpnstate.VPNAction
import com.vpn.fovix.domain.vpnstate.VPNState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class VPNViewModel(
    private val repository: VpnRepository
) : ViewModel() {


    private val _state = MutableStateFlow(
        VPNState(
            status = ConnectionStatus.DISCONNECTED
        )
    )


    val state: StateFlow<VPNState> = _state



    fun handleAction(action: VPNAction) {

        when(action) {

            VPNAction.Connect -> {
                connect()
            }


            VPNAction.Disconnect -> {
                disconnect()
            }

        }

    }



    private fun connect() {

        viewModelScope.launch {

            _state.value =
                _state.value.copy(
                    status = ConnectionStatus.CONNECTING
                )


            repository.connect()


            _state.value =
                _state.value.copy(
                    status = ConnectionStatus.CONNECTED
                )

        }

    }




    private fun disconnect() {

        viewModelScope.launch {

            repository.disconnect()


            _state.value =
                _state.value.copy(
                    status = ConnectionStatus.DISCONNECTED
                )

        }

    }


}