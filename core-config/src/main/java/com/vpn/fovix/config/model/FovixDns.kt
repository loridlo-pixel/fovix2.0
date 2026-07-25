package com.vpn.fovix.config.model


data class FovixDns(

    val servers: List<String> = listOf(
        "1.1.1.1",
        "8.8.8.8"
    )

)