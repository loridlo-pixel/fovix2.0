package com.vpn.fovix.config.model


data class FovixConfig(

    val inbound: FovixInbound = FovixInbound(),

    val outbounds: List<FovixOutbound>,

    val dns: FovixDns = FovixDns(),

    val route: FovixRoute = FovixRoute()

)