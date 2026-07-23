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
fun AdvancedHomeView(


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

            modifier = Modifier.size(32.dp)

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

            modifier = Modifier.size(32.dp)

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


                text = "SERVER",


                color = Color(0xFF8B98A8),


                fontSize = 12.sp


            )



            Spacer(

                modifier = Modifier.size(6.dp)

            )



            Text(


                text = state.server,


                color = Color.White,


                fontSize = 18.sp


            )





            Spacer(

                modifier = Modifier.size(18.dp)

            )





            Text(


                text = "NETWORK",


                color = Color(0xFF8B98A8),


                fontSize = 12.sp


            )





            Spacer(

                modifier = Modifier.size(6.dp)

            )





            Text(


                text = "Ping 32 ms",


                color = Color.White,


                fontSize = 16.sp


            )





            Text(


                text = "↓ ${state.download} Mbps   ↑ ${state.upload} Mbps",


                color = Color(0xFF00E5FF),


                fontSize = 14.sp


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