package com.vpn.fovix.app.presentation.servers


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun ServersScreen(


    selectedServer: String,


    onServerSelected: (String) -> Unit,


    onBack: () -> Unit


) {



    val servers = listOf(

        "Germany 🇩🇪",

        "Netherlands 🇳🇱",

        "Finland 🇫🇮",

        "USA 🇺🇸"

    )




    Column(


        modifier = Modifier

            .fillMaxSize()

            .padding(24.dp),



        horizontalAlignment = Alignment.CenterHorizontally,


        verticalArrangement = Arrangement.Top


    ) {



        Text(


            text = "←",


            color = Color(0xFFA855F7),


            fontSize = 34.sp,


            modifier = Modifier

                .align(Alignment.Start)

                .clickable {


                    onBack()


                }


        )





        Spacer(

            modifier = Modifier.height(20.dp)

        )






        Text(


            text = "FOVIX SERVERS",


            color = Color.White,


            fontSize = 30.sp


        )





        Spacer(

            modifier = Modifier.height(30.dp)

        )







        servers.forEach { server ->




            ServerGlassCard(


                name = server,


                selected = server == selectedServer,


                onClick = {


                    onServerSelected(server)


                }


            )





            Spacer(

                modifier = Modifier.height(16.dp)

            )




        }




    }



}








@Composable
private fun ServerGlassCard(


    name: String,


    selected: Boolean,


    onClick: () -> Unit


) {



    Column(


        modifier = Modifier

            .fillMaxWidth()

            .clip(

                RoundedCornerShape(24.dp)

            )

            .background(


                Brush.linearGradient(


                    colors = listOf(


                        Color(0x44FFFFFF),


                        Color(0x22151827)


                    )


                )

            )


            .border(


                width = 1.dp,


                brush = Brush.linearGradient(


                    colors = if(selected){


                        listOf(

                            Color(0xFFA855F7),

                            Color(0xFF38BDF8)

                        )


                    }
                    else {


                        listOf(

                            Color(0x33FFFFFF),

                            Color(0x11FFFFFF)

                        )


                    }


                ),


                shape = RoundedCornerShape(24.dp)


            )


            .clickable {


                onClick()


            }


            .padding(20.dp)



    ) {



        Text(


            text = if(selected)


                "●  $name"


            else


                "○  $name",



            color = Color.White,


            fontSize = 18.sp


        )





        Spacer(

            modifier = Modifier.height(8.dp)

        )





        Text(


            text = "42 ms   •   98 Mbps",


            color = Color(0xFF9AA7B5),


            fontSize = 14.sp


        )



    }


}