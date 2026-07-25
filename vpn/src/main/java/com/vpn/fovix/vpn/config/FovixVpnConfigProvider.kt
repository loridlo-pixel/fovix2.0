package com.vpn.fovix.vpn.config


import com.vpn.fovix.config.builder.SingBoxConfigBuilder
import com.vpn.fovix.config.factory.FovixProtocolFactory



object FovixVpnConfigProvider {


    fun build(): String {


        val config =

            FovixProtocolFactory.createVless(

                server = "ai.noooo.win",

                port = 443,

                uuid = "c5c1c20f-691d-4850-988c-ee463f4799ad",

                sni = "cdn-v1-6a51ff3b.noooo.win",

                fingerprint = "chrome"

            )



        return SingBoxConfigBuilder.build(

            config

        )

    }


}