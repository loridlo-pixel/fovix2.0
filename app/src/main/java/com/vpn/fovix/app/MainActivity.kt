package com.vpn.fovix.app


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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


    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)


        enableEdgeToEdge()


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



    HomeDashboard(

        connected =
            state.status == ConnectionStatus.CONNECTED,


        server =
            state.server ?: "Auto",


        download =
            state.download,


        upload =
            state.upload,


        onConnectClick = {

            viewModel.toggleConnection()

        }

    )


}