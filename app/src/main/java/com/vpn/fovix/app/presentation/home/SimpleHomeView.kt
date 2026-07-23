package com.vpn.fovix.app.presentation.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size

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
fun SimpleHomeView(


    state: HomeUiState,


    onConnect: () -> Unit,


    onDisconnect: () -> Unit,


    onOpenSubscriptions: () -> Unit


) {



    Column(


        horizontalAlignment = Alignment.CenterHorizontally,


        verticalArrangement = Arrangement.Center


    ) {



        Text(


            text = "FOVIX",


            color = Color.White,


            fontSize = 34.sp


        )





        Spacer(

            modifier = Modifier.size(40.dp)

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







        Text(


            text = "Smart VPN Protection",


            color = Color(0xFF9AA7B5),


            fontSize = 14.sp


        )







        Spacer(

            modifier = Modifier.size(24.dp)

        )







        FovixAddServerButton(


            onClick = onOpenSubscriptions


        )



    }



}