package com.vpn.fovix.app


import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

import com.vpn.fovix.app.presentation.FovixApp
import com.vpn.fovix.app.presentation.theme.FovixTheme



class MainActivity : ComponentActivity() {


    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)


        enableEdgeToEdge()



        setContent {


            FovixTheme {


                Surface(

                    modifier = Modifier
                        .fillMaxSize(),

                    color = androidx.compose.ui.graphics.Color.Transparent

                ) {


                    FovixApp()


                }

            }

        }

    }





    private fun enableEdgeToEdge() {


        WindowCompat.setDecorFitsSystemWindows(

            window,

            false

        )


        window.statusBarColor =

            android.graphics.Color.TRANSPARENT



        window.navigationBarColor =

            android.graphics.Color.rgb(
                7,
                24,
                39
            )



        val controller =

            WindowInsetsControllerCompat(

                window,

                window.decorView

            )



        controller.isAppearanceLightStatusBars = false

        controller.isAppearanceLightNavigationBars = false


    }


}