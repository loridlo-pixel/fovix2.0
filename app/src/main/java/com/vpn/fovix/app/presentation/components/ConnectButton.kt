package com.vpn.fovix.app.presentation.components


import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun ConnectButton(
    connected:Boolean,
    onClick:()->Unit
){


Button(
onClick = onClick
){

Text(
if(connected)
"DISCONNECT"
else
"CONNECT"
)


}


}

