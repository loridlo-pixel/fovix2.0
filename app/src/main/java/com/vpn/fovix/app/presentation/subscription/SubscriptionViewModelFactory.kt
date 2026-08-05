package com.vpn.fovix.app.presentation.subscription


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.vpn.fovix.data.importer.SubscriptionImportEngine
import com.vpn.fovix.data.subscription.SubscriptionRepository



class SubscriptionViewModelFactory(

    private val repository: SubscriptionRepository,

    private val importEngine: SubscriptionImportEngine

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

                repository,

                importEngine

            ) as T

        }



        throw IllegalArgumentException(

            "Unknown ViewModel"

        )

    }


}