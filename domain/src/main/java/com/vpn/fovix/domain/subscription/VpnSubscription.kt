package com.vpn.fovix.domain.subscription


import com.vpn.fovix.domain.server.ServerProfile


data class VpnSubscription(

    val id: String,

    val name: String,

    val url: String,

    val servers: List<ServerProfile> = emptyList(),

    val isActive: Boolean = true

)