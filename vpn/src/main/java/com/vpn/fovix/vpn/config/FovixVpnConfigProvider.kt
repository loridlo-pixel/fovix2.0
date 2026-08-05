package com.vpn.fovix.vpn.config


import com.vpn.fovix.config.builder.SingBoxConfigBuilder
import com.vpn.fovix.config.factory.FovixProtocolFactory
import com.vpn.fovix.domain.vpnprofile.VpnProfile



object FovixVpnConfigProvider {



    fun build(

        profile: VpnProfile

    ): String {



        val config =

            FovixProtocolFactory.createVless(


                server = profile.server,


                port = profile.port,


                uuid = profile.uuid,


                sni = profile.sni,


                fingerprint = profile.fingerprint


            )




        return SingBoxConfigBuilder.build(

            config

        )


    }


}