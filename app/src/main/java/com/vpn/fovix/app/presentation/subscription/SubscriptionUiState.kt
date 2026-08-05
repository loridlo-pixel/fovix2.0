package com.vpn.fovix.app.presentation.subscription


import com.vpn.fovix.domain.subscription.VpnSubscription


data class SubscriptionUiState(

    val subscriptions: List<VpnSubscription> = emptyList(),

    val selected: VpnSubscription? = null,

    val inputUrl: String = "",

    val isLoading: Boolean = false,

    val error: String? = null

) {

    // временная совместимость со старым SubscriptionScreen
    val input: String
        get() = inputUrl

}