package com.vpn.fovix.app.presentation.subscription


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vpn.fovix.data.importer.SubscriptionImportEngine
import com.vpn.fovix.data.repository.ServerRepository



class SubscriptionViewModelFactory(

    private val importEngine: SubscriptionImportEngine,

    private val serverRepository: ServerRepository

) : ViewModelProvider.Factory {



    override fun <T : ViewModel> create(

        modelClass: Class<T>

    ): T {



        if (

            modelClass.isAssignableFrom(
                SubscriptionViewModel::class.java
            )

        ) {


            return SubscriptionViewModel(

                importEngine,

                serverRepository

            ) as T


        }



        throw IllegalArgumentException(

            "Unknown ViewModel"

        )

    }


}