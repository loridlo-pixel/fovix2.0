package com.vpn.fovix.app.presentation.subscription


import com.vpn.fovix.domain.server.ServerProfile



data class SubscriptionUiState(

    val input: String = "",

    val loading: Boolean = false,

    val message: String = "",

    val servers: List<ServerProfile> = emptyList()

)