package com.vpn.fovix.app.presentation.home


import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.vpn.fovix.data.repository.VpnRepository
import com.vpn.fovix.domain.vpnprofile.VpnProfile

import kotlinx.coroutines.launch



class HomeViewModel(

    private val repository: VpnRepository

) : ViewModel() {



    private var currentProfile: VpnProfile? = null





    fun setProfile(
        profile: VpnProfile
    ){

        currentProfile = profile

    }






    fun toggleConnection(){


        Log.d(

            "FOVIX",

            "Button pressed"

        )



        currentProfile?.let {


            repository.toggle(

                it

            )


        }



    }



}