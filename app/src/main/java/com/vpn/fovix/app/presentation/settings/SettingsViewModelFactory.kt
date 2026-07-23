package com.vpn.fovix.app.presentation.settings


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.vpn.fovix.app.data.UserPreferences



class SettingsViewModelFactory(


    private val preferences: UserPreferences


) : ViewModelProvider.Factory {



    override fun <T : ViewModel> create(


        modelClass: Class<T>


    ): T {



        if(

            modelClass.isAssignableFrom(

                SettingsViewModel::class.java

            )

        ) {



            return SettingsViewModel(

                preferences

            ) as T


        }



        throw IllegalArgumentException(

            "Unknown ViewModel class"

        )


    }


}