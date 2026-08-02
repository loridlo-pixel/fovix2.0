package com.vpn.fovix.app.presentation.theme


import android.app.Activity

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme

import androidx.compose.runtime.Composable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView

import androidx.core.view.WindowCompat



private val FovixScheme = darkColorScheme(

    primary = Color(0xFFA855F7),

    secondary = Color(0xFF38BDF8),

    tertiary = Color(0xFF34D399),


    background = Color(0xFF0F111A),

    surface = Color(0xFF151827),


    onBackground = Color.White,

    onSurface = Color.White

)





@Composable
fun FovixTheme(

    content: @Composable () -> Unit

) {


    val view = LocalView.current



    if (!view.isInEditMode) {


        val window =

            (view.context as Activity)
                .window



        WindowCompat.setDecorFitsSystemWindows(

            window,

            false

        )



        window.statusBarColor =

            android.graphics.Color.TRANSPARENT



        window.navigationBarColor =

            android.graphics.Color.TRANSPARENT



        WindowCompat.getInsetsController(

            window,

            view

        ).isAppearanceLightStatusBars = false



        WindowCompat.getInsetsController(

            window,

            view

        ).isAppearanceLightNavigationBars = false


    }





    MaterialTheme(

        colorScheme = FovixScheme,

        typography = Typography(),

        content = content

    )


}