package com.vpn.fovix.app.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.vpn.fovix.data.repository.VpnRepository
import com.vpn.fovix.domain.vpnprofile.VpnProfile
import com.vpn.fovix.domain.vpnstate.VPNAction

import kotlinx.coroutines.launch




class VPNViewModel(

    private val repository: VpnRepository

) : ViewModel() {



    private var currentProfile: VpnProfile? = null





    fun setProfile(

        profile: VpnProfile

    ) {


        currentProfile = profile


    }








    fun handleAction(

        action: VPNAction

    ) {


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


        currentProfile?.let { profile ->



            viewModelScope.launch {


                repository.startVpn(

                    profile

                )


            }



        }


    }








    private fun disconnect() {



        viewModelScope.launch {


            repository.disconnect()


        }


    }



}