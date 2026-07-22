package com.vpn.fovix.domain.server


data class TLSConfig(

    val enabled: Boolean = false,

    val serverName: String? = null,

    val fingerprint: String? = null,

    val alpn: List<String> = emptyList(),

    val allowInsecure: Boolean = false

)