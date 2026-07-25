package com.vpn.fovix.config.model


data class FovixProfile(

    val id: String,

    val name: String,

    val proxy: FovixProxy,

    val tls: FovixTls? = null,

    val transport: FovixTransport? = null

)