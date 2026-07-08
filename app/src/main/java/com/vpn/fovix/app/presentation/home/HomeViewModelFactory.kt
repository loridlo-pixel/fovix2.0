package com.vpn.fovix.app.presentation.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vpn.fovix.data.repository.VpnRepository



class HomeViewModelFactory(

    private val repository: VpnRepository

): ViewModelProvider.Factory {



    override fun <T : ViewModel> create(

        modelClass: Class<T>

    ): T {


        if(
            modelClass.isAssignableFrom(
                HomeViewModel::class.java
            )
        ){

            return HomeViewModel(
                repository
            ) as T

        }


        throw IllegalArgumentException(
            "Unknown ViewModel"
        )

    }


}