package com.vpn.fovix.app.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vpn.fovix.data.repository.VpnRepository
import com.vpn.fovix.domain.vpnstate.VPNAction
import kotlinx.coroutines.flow.StateFlow


class VPNViewModel(
    private val repository: VpnRepository
): ViewModel(){


val state: StateFlow<com.vpn.fovix.domain.vpnstate.VPNState>
    = repository.state



fun action(action: VPNAction){

    when(action){

        VPNAction.Connect ->
            repository.connect()


        VPNAction.Disconnect ->
            repository.disconnect()

    }

}


}

