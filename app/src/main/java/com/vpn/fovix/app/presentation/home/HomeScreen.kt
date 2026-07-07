package com.vpn.fovix.app.presentation.home


import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.vpn.fovix.app.presentation.theme.FovixBackground


@Composable
fun HomeScreen(){


    Surface(
        color = FovixBackground
    ){

        Text(
            text = "FOVIX",
            color = Color.White
        )

    }


}

