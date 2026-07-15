package com.vpn.fovix.app.presentation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vpn.fovix.app.presentation.home.HomeScreenDynamic
import com.vpn.fovix.app.presentation.home.HomeViewModel
import com.vpn.fovix.app.presentation.home.HomeViewModelFactory
import com.vpn.fovix.app.presentation.home.toHomeUiState
import com.vpn.fovix.data.repository.VpnRepository



@Composable
fun FovixApp(

    repository: VpnRepository,

    onConnect: () -> Unit,

    onDisconnect: () -> Unit

) {



    val viewModel: HomeViewModel = viewModel(

        factory = HomeViewModelFactory(

            repository

        )

    )



    val vpnState by viewModel.state.collectAsState()



    val homeState =

        vpnState.toHomeUiState()



    HomeScreenDynamic(

        state = homeState,

        onConnect = onConnect,

        onDisconnect = onDisconnect

    )


}