package com.vpn.fovix.app.presentation.home


import com.vpn.fovix.domain.vpnstate.ConnectionStatus


data class ConnectionUiState(

    val text:String,

    val active:Boolean,

    val loading:Boolean

)


fun mapConnectionState(
    status: ConnectionStatus
): ConnectionUiState {


    return when(status){


        ConnectionStatus.DISCONNECTED ->
            ConnectionUiState(
                text = "CONNECT",
                active = false,
                loading = false
            )


        ConnectionStatus.CONNECTING ->
            ConnectionUiState(
                text = "CONNECTING...",
                active = false,
                loading = true
            )


        ConnectionStatus.CONNECTED ->
            ConnectionUiState(
                text = "CONNECTED",
                active = true,
                loading = false
            )


        ConnectionStatus.ERROR ->
            ConnectionUiState(
                text = "ERROR",
                active = false,
                loading = false
            )

    }

}
