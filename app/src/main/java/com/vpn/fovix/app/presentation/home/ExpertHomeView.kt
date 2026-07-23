package com.vpn.fovix.app.presentation.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.vpn.fovix.app.presentation.home.components.FovixAddServerButton
import com.vpn.fovix.app.presentation.home.components.FovixCoreButton




@Composable
fun ExpertHomeView(


    state: HomeUiState,


    onConnect: () -> Unit,


    onDisconnect: () -> Unit,


    onOpenSubscriptions: () -> Unit


) {



    Column(


        modifier = Modifier

            .fillMaxWidth(),



        horizontalAlignment = Alignment.CenterHorizontally,


        verticalArrangement = Arrangement.Center


    ) {





        Text(


            text = "FOVIX",


            color = Color.White,


            fontSize = 34.sp


        )







        Spacer(

            modifier = Modifier.size(24.dp)

        )







        FovixCoreButton(


            state = state.status,


            server = state.server,


            onClick = {


                if(state.connected) {


                    onDisconnect()


                }
                else {


                    onConnect()


                }


            }


        )







        Spacer(

            modifier = Modifier.size(24.dp)

        )







        Column(


            modifier = Modifier

                .fillMaxWidth()

                .background(

                    Color(0xFF151D26),

                    RoundedCornerShape(20.dp)

                )

                .padding(20.dp)


        ) {



            Text(


                text = "NETWORK DOCTOR",


                color = Color(0xFF00E5FF),


                fontSize = 14.sp


            )







            Spacer(

                modifier = Modifier.size(16.dp)

            )







            Text(


                text = "✓ VPN Engine: sing-box",


                color = Color.White,


                fontSize = 15.sp


            )







            Text(


                text = "✓ Protocol: VLESS",


                color = Color.White,


                fontSize = 15.sp


            )







            Text(


                text = "✓ DNS: Secure",


                color = Color.White,


                fontSize = 15.sp


            )







            Text(


                text = "✓ Routing: Auto",


                color = Color.White,


                fontSize = 15.sp


            )



        }







        Spacer(

            modifier = Modifier.size(20.dp)

        )







        Column(


            modifier = Modifier

                .fillMaxWidth()

                .background(

                    Color(0xFF111820),

                    RoundedCornerShape(20.dp)

                )

                .padding(20.dp)


        ) {



            Text(


                text = "DIAGNOSTICS LITE",


                color = Color(0xFF7C4DFF),


                fontSize = 14.sp


            )






            Spacer(

                modifier = Modifier.size(12.dp)

            )






            Text(


                text = "Latency: 32 ms",


                color = Color.White,


                fontSize = 15.sp


            )






            Text(


                text = "Packet Loss: 0%",


                color = Color.White,


                fontSize = 15.sp


            )






            Text(


                text = "Speed Test: Ready",


                color = Color(0xFF00E5FF),


                fontSize = 15.sp


            )



        }







        Spacer(

            modifier = Modifier.size(24.dp)

        )







        FovixAddServerButton(


            onClick = onOpenSubscriptions


        )



    }



}