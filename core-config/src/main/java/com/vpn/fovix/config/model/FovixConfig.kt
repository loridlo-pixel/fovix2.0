package com.vpn.fovix.config.model


/**
 * Основная модель конфигурации FOVIX.
 *
 * Не содержит логики генерации JSON.
 * Используется ProtocolFactory -> ConfigBuilder.
 */
data class FovixConfig(

    /*
        Protocol
     */

    val protocol: String = "vless",


    /*
        Server
     */

    val server: String,

    val port: Int,


    /*
        Authentication
     */

    val uuid: String,


    /*
        VLESS options
     */

    val flow: String? = "xtls-rprx-vision",


    /*
        TLS
     */

    val sni: String,

    val fingerprint: String = "chrome",


    /*
        Transport

        future:
        tcp
        ws
        grpc
        xhttp
     */

    val transport: String = "tcp",


    /*
        Network options
     */

    val packetEncoding: String = "xudp",


    /*
        Security

        future:
        reality
        tls
     */

    val security: String = "tls",


    /*
        DNS profile

        future:
        custom DNS
        adblock DNS
        secure DNS
     */

    val dnsMode: String = "secure"

)