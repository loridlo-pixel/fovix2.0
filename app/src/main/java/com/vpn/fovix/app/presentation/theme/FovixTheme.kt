package com.vpn.fovix.app.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable


private val FovixDarkColors = darkColorScheme(

    primary = FovixAccent,

    secondary = FovixGreen,

    background = FovixBackground,

    surface = FovixSurface,

    onPrimary = FovixBackground,

    onSecondary = FovixBackground,

    onBackground = FovixText,

    onSurface = FovixText

)


@Composable
fun FovixTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(

        colorScheme = FovixDarkColors,

        typography = FovixTypography,

        content = content

    )

}