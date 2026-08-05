package com.vpn.fovix.app.presentation.home


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.Surface

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


import com.vpn.fovix.app.presentation.home.components.NetworkHealthCard
import com.vpn.fovix.app.presentation.home.components.ProtectionScenario
import com.vpn.fovix.app.presentation.home.components.ProtectionScenarioCard
import com.vpn.fovix.app.presentation.home.components.ProtectionScoreCard
import com.vpn.fovix.app.presentation.home.components.SubscriptionCard
import com.vpn.fovix.app.presentation.home.components.UserProfileCard
import com.vpn.fovix.app.presentation.home.components.VyryxCoreCard

import com.vpn.fovix.domain.subscription.VpnSubscription
import com.vpn.fovix.domain.vpnstate.ConnectionStatus



@Composable
fun HomeDashboard(


    status: ConnectionStatus,


    server: String,


    download: Int,


    upload: Int,


    mode: UserMode,


    vpnSubscription: VpnSubscription? = null,


    scenario: ProtectionScenario = ProtectionScenario.EVERYDAY,


    onConnectClick: () -> Unit,


    onProfileClick: () -> Unit = {},


    onModeClick: () -> Unit = {},


    onScenarioClick: (ProtectionScenario) -> Unit = {},


    onOpenSubscriptions: () -> Unit = {}


) {


    Surface(

        modifier = Modifier.fillMaxSize(),

        color = Color(0xFFF5F7FA)

    ) {


        Column(

            modifier = Modifier

                .fillMaxSize()

                .verticalScroll(

                    rememberScrollState()

                )

                .padding(

                    horizontal = 18.dp,

                    vertical = 20.dp

                )

        ) {



            UserProfileCard(

                username = "Дмитрий",

                subscription =

                    if (vpnSubscription != null)

                        "Premium"

                    else

                        "No subscription",


                mode = mode,


                onProfileClick = {

                    onProfileClick()

                },


                onModeClick = {

                    onModeClick()

                }

            )





            Spacer(

                modifier = Modifier.padding(

                    top = 16.dp

                )

            )





            VyryxCoreCard(

                mode = mode,

                status = status,

                server = server,

                subscription = vpnSubscription,

                onClick = {

                    onConnectClick()

                }

            )





            Spacer(

                modifier = Modifier.padding(

                    top = 14.dp

                )

            )





            ProtectionScenarioCard(

                scenario = scenario,

                onClick = {

                    onScenarioClick(it)

                }

            )





            Spacer(

                modifier = Modifier.padding(

                    top = 14.dp

                )

            )





            SubscriptionCard(

                subscription = vpnSubscription,

                onAddClick = {

                    onOpenSubscriptions()

                }

            )





            Spacer(

                modifier = Modifier.padding(

                    top = 14.dp

                )

            )





            ProtectionScoreCard(

                score = 98

            )





            Spacer(

                modifier = Modifier.padding(

                    top = 14.dp

                )

            )





            NetworkHealthCard(

                latency = 42,

                speed = download

            )





            Spacer(

                modifier = Modifier.padding(

                    bottom = 30.dp

                )

            )

        }

    }

}