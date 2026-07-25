package com.vpn.fovix.config.factory


import com.vpn.fovix.config.model.FovixConfig
import com.vpn.fovix.config.model.FovixOutbound
import com.vpn.fovix.config.model.FovixProxy
import com.vpn.fovix.config.model.FovixTls


object FovixConfigFactory {


    fun createVless(

        server: String,

        port: Int,

        uuid: String,

        sni: String? = null,

        fingerprint: String? = null

    ): FovixConfig {


        val proxy = FovixProxy(

    type = "vless",

    server = server,

    serverPort = port,

    uuid = uuid,

    tls = FovixTls(

                enabled = true,

                serverName = sni,

                fingerprint = fingerprint

            )

        )


        val outbound = FovixOutbound(

            type = "vless",

            tag = "proxy",

            proxy = proxy

        )


        return FovixConfig(

            outbounds = listOf(

                outbound

            )

        )

    }

}