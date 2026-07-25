package com.vpn.fovix.config.factory


import com.vpn.fovix.config.model.FovixConfig
import com.vpn.fovix.config.model.FovixOutbound
import com.vpn.fovix.config.model.FovixProxy
import com.vpn.fovix.config.model.FovixTls



object FovixProtocolFactory {


    fun create(

        protocol: String,

        server: String,

        port: Int,

        uuid: String? = null,

        password: String? = null,

        sni: String? = null,

        fingerprint: String? = null

    ): FovixConfig {


        val proxy = FovixProxy(

            type = protocol,

            server = server,

            serverPort = port,

            uuid = uuid,

            password = password,

            tls = FovixTls(

                enabled = true,

                serverName = sni,

                fingerprint = fingerprint

            )

        )


        val outbound = FovixOutbound(

            type = protocol,

            tag = "proxy",

            proxy = proxy

        )


        return FovixConfig(

            outbounds = listOf(

                outbound

            )

        )

    }



    fun createVless(

        server: String,

        port: Int,

        uuid: String,

        sni: String? = null,

        fingerprint: String? = null

    ): FovixConfig {


        return create(

            protocol = "vless",

            server = server,

            port = port,

            uuid = uuid,

            sni = sni,

            fingerprint = fingerprint

        )

    }



    fun createVmess(

        server: String,

        port: Int,

        uuid: String,

        sni: String? = null

    ): FovixConfig {


        return create(

            protocol = "vmess",

            server = server,

            port = port,

            uuid = uuid,

            sni = sni

        )

    }



    fun createTrojan(

        server: String,

        port: Int,

        password: String,

        sni: String? = null

    ): FovixConfig {


        return create(

            protocol = "trojan",

            server = server,

            port = port,

            password = password,

            sni = sni

        )

    }


}