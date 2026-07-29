package com.vpn.fovix.config.factory


import com.vpn.fovix.config.model.FovixConfig


object FovixProtocolFactory {


    fun create(

        protocol: String,

        server: String,

        port: Int,

        uuid: String,

        sni: String,

        fingerprint: String? = "chrome"

    ): FovixConfig {


        return FovixConfig(

            protocol = protocol,

            server = server,

            port = port,

            uuid = uuid,

            sni = sni,

            fingerprint = fingerprint ?: "chrome"

        )

    }



    fun createVless(

        server: String,

        port: Int,

        uuid: String,

        sni: String? = null,

        fingerprint: String? = "chrome"

    ): FovixConfig {


        return FovixConfig(

            protocol = "vless",

            server = server,

            port = port,

            uuid = uuid,

            sni = sni ?: server,

            fingerprint = fingerprint ?: "chrome"

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

            sni = sni ?: server

        )

    }



    fun createTrojan(

        server: String,

        port: Int,

        password: String,

        sni: String? = null

    ): FovixConfig {


        return FovixConfig(

            protocol = "trojan",

            server = server,

            port = port,

            uuid = password,

            sni = sni ?: server

        )

    }

}