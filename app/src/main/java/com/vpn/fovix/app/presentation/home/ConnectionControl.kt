package com.vpn.fovix.app.presentation.home


import androidx.compose.runtime.Composable
import com.vpn.fovix.app.presentation.components.ConnectButton


@Composable
fun ConnectionControl(
connected:Boolean,
onClick:()->Unit
){


ConnectButton(
connected = connected,
onClick = onClick
)


}

