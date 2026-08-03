package com.vpn.fovix.app.presentation.home


import androidx.compose.runtime.Composable
import com.vpn.fovix.app.presentation.home.components.VyryxCoreCard


@Composable
fun SimpleHomeView(

    state: HomeUiState,

    onConnect: () -> Unit,

    onDisconnect: () -> Unit,

    onOpenSubscriptions: () -> Unit

) {


    VyryxCoreCard(

        mode = UserMode.SIMPLE,

        status = state.status,

        server = state.server,

        onClick = {

            if(state.connected){

                onDisconnect()

            } else {

                onConnect()

            }

        }

    )

}