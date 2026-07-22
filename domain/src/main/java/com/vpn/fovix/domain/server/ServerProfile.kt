package com.vpn.fovix.domain.server


data class ServerProfile(

    val id: String,

    val name: String,

    val host: String,

    val port: Int,

    val country: String,

    val latency: Int,

    val load: Int,

    val enabled: Boolean

)