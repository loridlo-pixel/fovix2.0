package com.vpn.fovix.app.presentation.home


import android.util.Log
import androidx.lifecycle.ViewModel
import com.vpn.fovix.data.repository.VpnRepository
import com.vpn.fovix.domain.vpnstate.VPNState
import kotlinx.coroutines.flow.StateFlow



class HomeViewModel(

    private val repository: VpnRepository

) : ViewModel() {



    val state: StateFlow<VPNState> =

        repository.state



    fun toggleConnection() {


        Log.d(
            "FOVIX",
            "Button pressed"
        )


        repository.toggle()


    }


}
