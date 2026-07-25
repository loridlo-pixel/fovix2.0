package com.vpn.fovix.config.model


data class FovixOutbound(

    val type: String,

    val tag: String,

    val proxy: FovixProxy? = null,

    val transport: FovixTransport? = null

)