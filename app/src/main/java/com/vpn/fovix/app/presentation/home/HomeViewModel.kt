package com.vpn.fovix.app.presentation.home


import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.vpn.fovix.app.data.UserPreferences
import com.vpn.fovix.data.repository.VpnRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn



class HomeViewModel(


    private val repository: VpnRepository,


    preferences: UserPreferences


) : ViewModel() {



    private val selectedServer =

        MutableStateFlow(

            "Auto"

        )





    val state: StateFlow<HomeUiState> =



        combine(


            repository.state,


            preferences.userMode,


            selectedServer



        ) { vpnState, mode, server ->



            HomeUiState(


                status = vpnState.status,


                connected =

                    vpnState.status.name == "CONNECTED",



                server = server,



                userMode = mode,



                showServer = server != "Auto"



            )


        }



        .stateIn(


            viewModelScope,


            SharingStarted.WhileSubscribed(5000),


            HomeUiState()


        )








    fun selectServer(


        server: String


    ) {


        selectedServer.value = server


    }








    fun toggleConnection() {



        Log.d(


            "FOVIX",


            "Button pressed"


        )



        repository.toggle()



    }



}