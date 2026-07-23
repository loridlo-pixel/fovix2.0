package com.vpn.fovix.app.presentation.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.vpn.fovix.app.data.UserPreferences

import com.vpn.fovix.data.repository.VpnRepository



class HomeViewModelFactory(


    private val repository: VpnRepository,


    private val userPreferences: UserPreferences


) : ViewModelProvider.Factory {



    override fun <T : ViewModel> create(


        modelClass: Class<T>


    ): T {



        if(


            modelClass.isAssignableFrom(


                HomeViewModel::class.java


            )


        ) {



            return HomeViewModel(


                repository,


                userPreferences


            ) as T


        }




        throw IllegalArgumentException(

            "Unknown ViewModel"

        )


    }


}