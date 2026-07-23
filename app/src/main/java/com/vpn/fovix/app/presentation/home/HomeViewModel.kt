package com.vpn.fovix.app.presentation.home


import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.vpn.fovix.app.data.UserPreferences

import com.vpn.fovix.data.repository.VpnRepository

import com.vpn.fovix.domain.vpnstate.VPNState

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn



class HomeViewModel(


    private val repository: VpnRepository,


    preferences: UserPreferences


) : ViewModel() {



    val state: StateFlow<HomeUiState> =



        combine(


            repository.state,


            preferences.userMode



        ) { vpnState, mode ->



            HomeUiState(


                status = vpnState.status,


                connected =

                    vpnState.status.name == "CONNECTED",



                server = "Auto",



                userMode = mode



            )


        }


        .stateIn(


            viewModelScope,


            SharingStarted.WhileSubscribed(5000),


            HomeUiState()


        )







    fun toggleConnection() {



        Log.d(

            "FOVIX",

            "Button pressed"

        )



        repository.toggle()



    }



}