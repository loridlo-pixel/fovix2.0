package com.vpn.fovix.app.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vpn.fovix.data.repository.VpnRepository
import com.vpn.fovix.domain.vpnstate.ConnectionStatus
import com.vpn.fovix.domain.vpnstate.VPNAction
import com.vpn.fovix.domain.vpnstate.VPNState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch



class VPNViewModel(

    private val repository: VpnRepository

) : ViewModel() {



    val state: StateFlow<VPNState> =

        repository.state





    fun handleAction(

        action: VPNAction

    ) {


        when(action) {


            VPNAction.Connect -> {

                startVpn()

            }



            VPNAction.Disconnect -> {

                disconnect()

            }


        }


    }





    private fun startVpn() {


        viewModelScope.launch {


            repository.startVpn()


        }


    }





    private fun disconnect() {


        viewModelScope.launch {


            repository.disconnect()


        }


    }


}