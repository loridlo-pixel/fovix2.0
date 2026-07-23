package com.vpn.fovix.app.presentation.settings


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.vpn.fovix.app.data.UserPreferences
import com.vpn.fovix.app.presentation.home.UserMode

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

import kotlinx.coroutines.launch



class SettingsViewModel(


    private val preferences: UserPreferences


) : ViewModel() {



    val userMode =

        preferences.userMode.stateIn(


            viewModelScope,


            SharingStarted.WhileSubscribed(5000),


            UserMode.SIMPLE


        )







    fun setMode(


        mode: UserMode


    ) {


        viewModelScope.launch {


            preferences.setUserMode(

                mode

            )


        }


    }



}