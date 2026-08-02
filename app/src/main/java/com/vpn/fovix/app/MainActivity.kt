package com.vpn.fovix.app


import android.net.VpnService
import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

import com.vpn.fovix.app.presentation.FovixApp
import com.vpn.fovix.app.presentation.theme.FovixTheme

import com.vpn.fovix.vpn.SingBoxNative



class MainActivity : ComponentActivity() {


    companion object {

        private const val TAG = "FOVIX_MAIN"

    }



    private lateinit var appContainer: AppContainer



    private val vpnPermissionLauncher =

        registerForActivityResult(

            ActivityResultContracts.StartActivityForResult()

        ) {


            if(it.resultCode == RESULT_OK) {


                appContainer
                    .vpnRepository
                    .startVpn()


            }

        }





    override fun onCreate(

        savedInstanceState: Bundle?

    ) {


        super.onCreate(savedInstanceState)



        enableEdgeToEdge()



        appContainer =

            (application as FovixApplication)
                .container




        setContent {


            FovixTheme {


                Surface(

                    modifier = Modifier.fillMaxSize(),

                    color = androidx.compose.ui.graphics.Color.Transparent

                ) {


                    FovixApp(

vpnRepository = appContainer.vpnRepository
)


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

            android.graphics.Color.BLACK



        val controller =

            WindowInsetsControllerCompat(

                window,

                window.decorView

            )



        controller.isAppearanceLightStatusBars = true


        controller.isAppearanceLightNavigationBars = false


    }



}