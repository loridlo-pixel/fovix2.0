package com.vpn.fovix.app.presentation.home

import androidx.compose.material3.*
import androidx.compose.runtime.Composable


@Composable
fun SpeedMetrics(
    download:Int,
    upload:Int
){

    Card {

        Text(
            text =
            "v ${download} Mbps   ^ ${upload} Mbps"
        )

    }

}
