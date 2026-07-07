package com.vpn.fovix.app.presentation.home

import androidx.compose.material3.*
import androidx.compose.runtime.Composable


@Composable
fun ServerCard(
    server:String
){

    Card {

        Text(
            text = "Server: $server"
        )

    }

}
