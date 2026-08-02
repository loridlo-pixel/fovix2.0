package com.vpn.fovix.app.presentation.home


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height

import androidx.compose.material3.Surface

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


import com.vpn.fovix.app.presentation.home.components.UserProfileCard
import com.vpn.fovix.app.presentation.home.components.VyryxCoreCard
import com.vpn.fovix.app.presentation.home.components.ProtectionScoreCard
import com.vpn.fovix.app.presentation.home.components.NetworkHealthCard

import com.vpn.fovix.domain.vpnstate.ConnectionStatus



@Composable
fun HomeDashboard(


    status: ConnectionStatus,


    server: String,


    download: Int,


    upload: Int,


    onConnectClick: () -> Unit


) {



    Surface(


        modifier = Modifier

            .fillMaxSize(),


        color = Color(0xFF080C12)


    ) {



        Column(


            modifier = Modifier

                .fillMaxSize()

                .padding(

                    horizontal = 12.dp,

                    vertical = 12.dp

                )


        ) {



            /*
             *
             * USER PROFILE
             *
             */


            UserProfileCard(


                username = "Дмитрий",


                subscription = "Premium until 02.08.2027",


                plan = "PREMIUM"


            )





            Spacer(

                modifier = Modifier.height(14.dp)

            )





            /*
             *
             * MAIN VPN CORE
             *
             */


            VyryxCoreCard(


                status = status,


                server = server,


                onClick = {


                    onConnectClick()


                }


            )





            Spacer(

                modifier = Modifier.height(12.dp)

            )





            /*
             *
             * PROTECTION
             *
             */


            ProtectionScoreCard(


                score = 98


            )





            Spacer(

                modifier = Modifier.height(12.dp)

            )





            /*
             *
             * NETWORK HEALTH
             *
             */


            NetworkHealthCard(


                latency = 42,


                speed = download


            )



        }


    }



}