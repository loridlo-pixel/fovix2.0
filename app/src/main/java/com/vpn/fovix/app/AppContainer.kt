package com.vpn.fovix.app


import android.content.Context

import com.vpn.fovix.app.data.UserPreferences

import com.vpn.fovix.data.importer.SubscriptionImportEngine
import com.vpn.fovix.data.repository.ServerRepository
import com.vpn.fovix.data.repository.VpnRepository

import com.vpn.fovix.data.subscription.SubscriptionRepository
import com.vpn.fovix.data.subscription.SubscriptionRepositoryImpl

import com.vpn.fovix.vpn.VpnEngine



class AppContainer(

    private val context: Context

) {



    private val vpnEngine: VpnEngine by lazy {


        VpnEngine(

            context

        )


    }






    val vpnRepository: VpnRepository by lazy {


        VpnRepository(

            vpnEngine

        )


    }






    val serverRepository: ServerRepository by lazy {


        ServerRepository()


    }






    val subscriptionImportEngine: SubscriptionImportEngine by lazy {


        SubscriptionImportEngine()


    }






    val subscriptionRepository: SubscriptionRepository by lazy {


        SubscriptionRepositoryImpl(

            context

        )


    }






    val userPreferences: UserPreferences by lazy {


        UserPreferences(

            context

        )


    }



}