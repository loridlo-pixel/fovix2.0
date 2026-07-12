package com.vpn.fovix.vpn


data class VPNServer(

    val protocol: String,

    val address: String,

    val port: Int,

    val uuid: String? = null,

    val password: String? = null,

    val security: String? = null,

    val sni: String? = null,

    val transport: String? = null,

    val path: String? = null,

    val fingerprint: String? = null

)