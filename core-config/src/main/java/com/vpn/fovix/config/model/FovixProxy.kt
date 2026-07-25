package com.vpn.fovix.config.model


data class FovixProxy(

    /**
     * Proxy protocol:
     * vless
     * vmess
     * trojan
     * shadowsocks
     */
    val type: String,

    /**
     * Internal FOVIX outbound tag
     */
    val tag: String = "proxy",

    /**
     * Remote server address
     */
    val server: String,

    /**
     * Remote server port
     */
    val serverPort: Int,


    /**
     * VLESS / VMess user id
     */
    val uuid: String? = null,


    /**
     * Trojan password
     * Shadowsocks password
     */
    val password: String? = null,


    /**
     * VMess compatibility
     */
    val alterId: Int? = null,


    /**
     * Encryption/security:
     * auto
     * aes-128-gcm
     * chacha20-poly1305
     */
    val security: String? = null,


    /**
     * TLS / Reality configuration
     */
    val tls: FovixTls? = null,


    /**
     * Network transport:
     * tcp
     * ws
     * grpc
     * httpupgrade
     */
    val transport: FovixTransport? = null

)