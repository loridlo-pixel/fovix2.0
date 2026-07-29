package com.vpn.fovix.config.factory


import com.vpn.fovix.config.model.FovixConfig


object FovixConfigFactory {


    fun createVless(

        server: String,

        port: Int,

        uuid: String,

        sni: String? = null,

        fingerprint: String? = null

    ): FovixConfig {


        return FovixConfig(

            protocol = "vless",

            server = server,

            port = port,

            uuid = uuid,

            flow = "xtls-rprx-vision",

            sni = sni ?: server,

            fingerprint = fingerprint ?: "chrome",

            transport = "tcp",

            packetEncoding = "xudp",

            security = "tls"

        )

    }

}