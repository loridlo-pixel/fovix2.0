package com.vpn.fovix.app.presentation.home


import androidx.compose.runtime.Composable



@Composable
fun HomeModeRenderer(

    mode: String

) {


    when(mode) {


        "ADVANCED" -> {

            AdvancedView()

        }


        "EXPERT" -> {

            ExpertView()

        }


        else -> {

            SimpleView()

        }


    }

}
