package com.vpn.fovix.domain.server


data class Subscription(

    val id: String,

    val name: String,

    val url: String,

    val servers: List<ServerProfile> = emptyList()

)