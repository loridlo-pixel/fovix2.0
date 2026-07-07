package com.vpn.fovix.app.presentation.home.components

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun ServerCard(
    server:String
){

    Card {

        Text(
            text = server
        )

    }

}
