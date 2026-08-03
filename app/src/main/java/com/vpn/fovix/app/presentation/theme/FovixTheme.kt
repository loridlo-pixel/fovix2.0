package com.vpn.fovix.app.presentation.theme


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color



private val FovixScheme = darkColorScheme(

    primary = FovixColors.Accent,

    secondary = FovixColors.Accent,

    background = FovixColors.Accent,

    surface = Color.White,

    onPrimary = Color.White,

    onBackground = Color.White,

    onSurface = FovixColors.TextPrimary

)



@Composable
fun FovixTheme(

    content: @Composable () -> Unit

) {


    MaterialTheme(

        colorScheme = FovixScheme

    ) {


        Box(

            modifier = Modifier

                .fillMaxSize()

                .background(

                    FovixColors.Accent

                )

        ) {


            content()


        }


    }


}