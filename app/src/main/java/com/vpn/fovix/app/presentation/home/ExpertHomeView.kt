package com.vpn.fovix.app.presentation.home


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import com.vpn.fovix.app.presentation.home.components.NetworkHealthCard
import com.vpn.fovix.app.presentation.home.components.ProtectionScoreCard
import com.vpn.fovix.app.presentation.home.components.ServerCard
import com.vpn.fovix.app.presentation.home.components.VyryxCoreCard
import com.vpn.fovix.app.presentation.home.components.VyryxHeader
import com.vpn.fovix.app.presentation.home.components.VyryxHomeLayout



@Composable
fun ExpertHomeView(

    state: HomeUiState,

    onConnect: () -> Unit,

    onDisconnect: () -> Unit,

    onOpenSubscriptions: () -> Unit,

    onOpenServers: () -> Unit

) {


    VyryxHomeLayout(


        header = {

            VyryxHeader()

        },


        core = {


            VyryxCoreCard(

                connected = state.connected,

                onClick = {

                    if(state.connected){

                        onDisconnect()

                    } else {

                        onConnect()

                    }

                }

            )


        },


        content = {


            ServerCard(

                server = state.server,

                onClick = onOpenServers

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

                latency = 28,

                speed = state.download

            )


            Spacer(

                modifier = Modifier.height(20.dp)

            )


            Text(

                text = """
                    Expert Security Controls
                    
                    DNS Protection
                    Smart Routing
                    Auto Recovery
                """.trimIndent(),


                color = Color(0xFF6B7280),

                fontSize = 14.sp

            )


        }


    )

}