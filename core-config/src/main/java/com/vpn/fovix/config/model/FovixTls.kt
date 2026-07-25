package com.vpn.fovix.config.model


data class FovixTls(

    val enabled: Boolean = true,

    val serverName: String? = null,

    val fingerprint: String? = null

)