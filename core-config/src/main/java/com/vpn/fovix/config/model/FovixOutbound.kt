package com.vpn.fovix.config.model


data class FovixOutbound(

    val tag: String,

    val type: String,

    val proxy: FovixProxy? = null

)