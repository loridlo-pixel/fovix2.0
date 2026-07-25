package com.vpn.fovix.config.model


data class FovixConfig(

    val proxies: List<FovixProxy>,

    val finalOutbound: String = "proxy"

)