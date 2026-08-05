package com.vpn.fovix.app.presentation.subscription


import com.vpn.fovix.domain.subscription.VpnSubscription



data class SubscriptionUiState(

    val subscriptions: List<VpnSubscription> = emptyList(),

    val selected: VpnSubscription? = null,

    /**
     * Временное поле для старого экрана импорта.
     * Позже заменим на BottomSheet Add Subscription.
     */
    val input: String = "",

    val isLoading: Boolean = false,

    val error: String? = null

)