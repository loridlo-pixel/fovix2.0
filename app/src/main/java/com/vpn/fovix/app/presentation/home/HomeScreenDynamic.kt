package com.vpn.fovix.app.presentation.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.vpn.fovix.app.presentation.home.components.FovixAddServerButton
import com.vpn.fovix.app.presentation.home.components.FovixCoreButton
import com.vpn.fovix.app.presentation.home.components.MetricsCard
import com.vpn.fovix.app.presentation.home.components.ServerCard
import com.vpn.fovix.app.presentation.home.components.StatusCard



@Composable
fun HomeScreenDynamic(


    state: HomeUiState,


    onConnect: () -> Unit,


    onDisconnect: () -> Unit,


    onOpenSubscriptions: () -> Unit


) {



    Column(


        modifier = Modifier

            .fillMaxSize()

            .background(

                Color(0xFF0B1015)

            )

            .padding(24.dp),



        horizontalAlignment = Alignment.CenterHorizontally,


        verticalArrangement = Arrangement.Center


    ) {



        Text(


            text = "FOVIX",


            color = Color.White,


            fontSize = 32.sp



        )



        Spacer(

            modifier = Modifier.size(12.dp)

        )





        Text(


            text = "Secure Connection",


            color = Color(0xFF8B98A8),


            fontSize = 14.sp



        )





        Spacer(

            modifier = Modifier.size(32.dp)

        )






        FovixCoreButton(


            state = state.status,


            server = state.server,


            onClick = {


                if(state.connected){


                    onDisconnect()


                }

                else {


                    onConnect()


                }


            }


        )







        Spacer(

            modifier = Modifier.size(28.dp)

        )






        Card(


            modifier = Modifier,


            colors = CardDefaults.cardColors(


                containerColor = Color(0xFF111820)


            ),


            shape = RoundedCornerShape(20.dp)


        ){



            Column(


                modifier = Modifier.padding(20.dp),


                horizontalAlignment = Alignment.CenterHorizontally


            ){



                Text(


                    text = if(state.connected)

                        "PROTECTED"

                    else

                        "DISCONNECTED",


                    color = if(state.connected)

                        Color(0xFF00E5FF)

                    else

                        Color(0xFFFF6B6B),


                    fontSize = 18.sp


                )



                Spacer(

                    modifier = Modifier.size(8.dp)

                )



                Text(


                    text = state.server,


                    color = Color.White,


                    fontSize = 15.sp


                )



            }



        }





        Spacer(

            modifier = Modifier.size(16.dp)

        )





        if(state.showMetrics){


            MetricsCard(

                ping = "Ping 40 ms"

            )


        }






        Spacer(

            modifier = Modifier.size(20.dp)

        )





        FovixAddServerButton(


            onClick = onOpenSubscriptions


        )





    }


}