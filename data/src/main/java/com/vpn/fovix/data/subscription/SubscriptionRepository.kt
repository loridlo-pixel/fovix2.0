package com.vpn.fovix.data.subscription


import com.vpn.fovix.domain.subscription.VpnSubscription

import kotlinx.coroutines.flow.StateFlow


interface SubscriptionRepository {


    val subscriptions:
            StateFlow<List<VpnSubscription>>


    fun getSubscriptions():
            List<VpnSubscription>


    fun addSubscription(
        subscription: VpnSubscription
    )


    fun removeSubscription(
        id: String
    )


    fun getActive():
            VpnSubscription?



}