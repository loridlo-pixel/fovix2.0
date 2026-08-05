package com.vpn.fovix.app.presentation.subscription

import com.vpn.fovix.domain.subscription.VpnSubscription


data class SubscriptionUiState(

    val subscriptions: List<VpnSubscription> = emptyList(),

    val selected: VpnSubscription? = null,

    val url: String = "",

    val isLoading: Boolean = false,

    val error: String? = null

)