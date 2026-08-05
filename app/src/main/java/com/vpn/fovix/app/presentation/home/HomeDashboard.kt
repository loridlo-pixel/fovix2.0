package com.vpn.fovix.app.presentation.home


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

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


    onAddSubscriptionClick: () -> Unit = {}



) {



    Surface(


        modifier = Modifier

            .fillMaxSize(),


        color = Color(0xFFF5F7FA)


    ) {



        Column(


            modifier = Modifier

                .fillMaxSize()

                .padding(

                    horizontal = 18.dp,

                    vertical = 20.dp

                )

        ) {



            /*
             * FOVIX subscription
             * Это подписка приложения:
             * Free / Expert / Premium
             */

            UserProfileCard(


                username = "Дмитрий",


                subscription = "Premium until 02.08.2027",


                mode = mode,


                onProfileClick = {

                    onProfileClick()

                },


                onModeClick = {

                    onModeClick()

                }


            )





            Spacer(

                modifier = Modifier.height(16.dp)

            )





            /*
             * VYRYX Core
             * Главное состояние защиты
             */

            VyryxCoreCard(


                mode = mode,


                status = status,


                server = server,


                onClick = {

                    onConnectClick()

                }


            )





            Spacer(

                modifier = Modifier.height(14.dp)

            )





            ProtectionScenarioCard(


                scenario = scenario,


                onClick = {

                    onScenarioClick(it)

                }


            )





            Spacer(

                modifier = Modifier.height(14.dp)

            )





            /*
             * VPN Provider subscription
             *
             * Это НЕ подписка VYRYX.
             * Это внешний VPN источник:
             * VLESS / VMess / Trojan provider
             */

            SubscriptionCard(


                subscription = vpnSubscription,


                onAddClick = {


                    onAddSubscriptionClick()


                }


            )





            Spacer(

                modifier = Modifier.height(14.dp)

            )





            ProtectionScoreCard(


                score = 98


            )





            Spacer(

                modifier = Modifier.height(14.dp)

            )





            NetworkHealthCard(


                latency = 42,


                speed = download


            )


        }

    }

}