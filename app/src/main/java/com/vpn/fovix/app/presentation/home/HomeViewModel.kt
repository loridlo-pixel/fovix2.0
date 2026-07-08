package com.vpn.fovix.app.presentation.home

import androidx.lifecycle.ViewModel
import com.vpn.fovix.data.repository.VpnRepository
import com.vpn.fovix.core.decision.UserMode
import com.vpn.fovix.domain.vpnstate.ConnectionStatus
import com.vpn.fovix.domain.vpnstate.VPNState
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(

    private val repository: VpnRepository

) : ViewModel() {

    val state: StateFlow<VPNState> =
        repository.state

    fun toggleConnection() {

        if (state.value.status == ConnectionStatus.CONNECTED) {

            repository.disconnect()

        } else {

            repository.connect(UserMode.SIMPLE)

        }

    }

}