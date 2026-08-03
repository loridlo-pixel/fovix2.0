package com.vpn.fovix.app.presentation.home


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


import com.vpn.fovix.app.presentation.home.components.NetworkHealthCard
import com.vpn.fovix.app.presentation.home.components.ProtectionScoreCard
import com.vpn.fovix.app.presentation.home.components.UserProfileCard
import com.vpn.fovix.app.presentation.home.components.VyryxCoreCard

import com.vpn.fovix.domain.vpnstate.ConnectionStatus



@Composable
fun HomeDashboard(


    status: ConnectionStatus,


    server: String,


    download: Int,


    upload: Int,


    onConnectClick: () -> Unit,


    onProfileClick: () -> Unit = {},


    onModeClick: () -> Unit = {}



) {



    Surface(


        modifier = Modifier

            .fillMaxSize(),


        color = MaterialTheme.colorScheme.background



    ) {



        Column(



            modifier = Modifier

                .fillMaxSize()

                .padding(

                    horizontal = 20.dp,

                    vertical = 24.dp

                )



        ) {



            UserProfileCard(



                username = "Дмитрий",



                subscription = "Premium until 02.08.2027",



                mode = UserMode.SIMPLE,



                onProfileClick = {



                    onProfileClick()



                },



                onModeClick = {



                    onModeClick()



                }



            )







            Spacer(



                modifier = Modifier.height(18.dp)



            )







            VyryxCoreCard(



                status = status,



                server = server,



                onClick = {



                    onConnectClick()



                }



            )







            Spacer(



                modifier = Modifier.height(16.dp)



            )







            ProtectionScoreCard(



                score = 98



            )







            Spacer(



                modifier = Modifier.height(16.dp)



            )







            NetworkHealthCard(



                latency = 42,



                speed = download



            )



        }



    }



}