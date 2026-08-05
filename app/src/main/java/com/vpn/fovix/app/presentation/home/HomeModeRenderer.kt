package com.vpn.fovix.app.presentation.home


import androidx.compose.runtime.Composable

import com.vpn.fovix.domain.subscription.VpnSubscription
import com.vpn.fovix.domain.vpnstate.ConnectionStatus



@Composable
fun HomeModeRenderer(


    mode: UserMode,


    status: ConnectionStatus,


    server: String,


    download: Int,


    upload: Int,


    subscription: VpnSubscription?,


    onConnectClick: () -> Unit,


    onOpenSubscriptions: () -> Unit


) {



    HomeDashboard(


        mode = mode,


        status = status,


        server = server,


        download = download,


        upload = upload,


        vpnSubscription = subscription,


        onConnectClick = onConnectClick,


        onOpenSubscriptions = onOpenSubscriptions


    )


}