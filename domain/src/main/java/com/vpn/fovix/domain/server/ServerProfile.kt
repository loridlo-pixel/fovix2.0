package com.vpn.fovix.domain.server


data class ServerProfile(

    val id: String,

    val name: String,

    val protocol: Protocol,

    val address: String,

    val port: Int,

    val uuid: String? = null,

    val transport: Transport = Transport.UNKNOWN,

    val tls: TLSConfig = TLSConfig(),

    val path: String? = null,

    val host: String? = null,

    val networkMode: String? = null,

    val subscriptionId: String? = null

)