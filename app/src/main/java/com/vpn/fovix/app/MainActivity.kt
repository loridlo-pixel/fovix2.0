package com.vpn.fovix.app


import android.net.VpnService
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vpn.fovix.app.presentation.home.HomeDashboard
import com.vpn.fovix.app.presentation.home.HomeViewModel
import com.vpn.fovix.app.presentation.home.HomeViewModelFactory
import com.vpn.fovix.data.repository.VpnRepository
import com.vpn.fovix.domain.vpnstate.ConnectionStatus
import com.vpn.fovix.ui.theme.FovixTheme


class MainActivity : ComponentActivity() {


    private var vpnPermissionGranted = false


    private val vpnPermissionLauncher =

        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {

            if (it.resultCode == RESULT_OK) {

                vpnPermissionGranted = true

                Log.d(
                    "FOVIX",
                    "VPN PERMISSION GRANTED"
                )

            } else {

                Log.e(
                    "FOVIX",
                    "VPN PERMISSION DENIED"
                )

            }

        }


    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)


        /*
         * Load sing-box native engine
         */
        try {

            LibboxLoader.load()

            Log.d(
                "FOVIX",
                "libbox.so loaded successfully"
            )

        } catch (e: Exception) {

            Log.e(
                "FOVIX",
                "libbox loading failed",
                e
            )

        }


        enableEdgeToEdge()



        val permissionIntent =

            VpnService.prepare(this)



        if (permissionIntent == null) {

            vpnPermissionGranted = true

            Log.d(
                "FOVIX",
                "VPN PERMISSION ALREADY GRANTED"
            )


        } else {


            Log.d(
                "FOVIX",
                "REQUEST VPN PERMISSION"
            )


            vpnPermissionLauncher.launch(
                permissionIntent
            )

        }



        val container =

            (application as FovixApplication)
                .container



        setContent {


            FovixTheme {


                FovixRoot(

                    repository = container.vpnRepository

                )

            }

        }

    }

}





@Composable
fun FovixRoot(

    repository: VpnRepository

) {


    val viewModel: HomeViewModel =

        viewModel(

            factory = HomeViewModelFactory(
                repository
            )

        )


    val state by viewModel.state.collectAsState()



    Log.d(
        "FOVIX",
        "UI STATE = ${state.status}"
    )



    HomeDashboard(


        connected =

            state.status == ConnectionStatus.CONNECTED,


        server =

            state.server,


        download =

            state.download,


        upload =

            state.upload,


        onConnectClick = {


            Log.e(
                "FOVIX_TEST",
                "MAIN CALLBACK RECEIVED"
            )


            viewModel.toggleConnection()


        }

    )

}