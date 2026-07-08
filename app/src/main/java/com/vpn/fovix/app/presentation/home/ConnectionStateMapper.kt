package com.vpn.fovix.app.presentation.home


import com.vpn.fovix.domain.vpnstate.ConnectionStatus



fun ConnectionStatus.toConnectionText(): String {


    return when(this) {


        ConnectionStatus.DISCONNECTED ->

            "OFF"



        ConnectionStatus.CONNECTING ->

            "CONNECTING"



        ConnectionStatus.CONNECTED ->

            "ON"



        ConnectionStatus.DISCONNECTING ->

            "DISCONNECTING"



        ConnectionStatus.ERROR ->

            "ERROR"

    }

}