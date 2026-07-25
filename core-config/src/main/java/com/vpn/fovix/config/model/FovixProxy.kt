package com.vpn.fovix.config.model


data class FovixProxy(

    val type: String,

    val tag: String = "proxy",

    val server: String,

    val serverPort: Int,


    // VLESS
    val uuid: String? = null,


    // VMess / Trojan / Shadowsocks
    val password: String? = null,


    // TLS
    val tls: FovixTls? = null,


    // Transport
    val transport: FovixTransport? = null

)