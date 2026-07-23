package com.vpn.fovix.app.presentation.settings


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.vpn.fovix.app.presentation.home.UserMode




@Composable
fun SettingsScreen(


    mode: UserMode,


    onModeChange: (UserMode) -> Unit,


    onBack: () -> Unit


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


            text = "←",


            color = Color(0xFF00E5FF),


            fontSize = 32.sp,


            modifier = Modifier

                .clickable {

                    onBack()

                }


        )







        Spacer(

            modifier = Modifier.size(24.dp)

        )







        Text(


            text = "FOVIX Settings",


            color = Color.White,


            fontSize = 28.sp


        )







        Spacer(

            modifier = Modifier.size(32.dp)

        )







        Text(


            text = "User Experience",


            color = Color(0xFF00E5FF),


            fontSize = 18.sp


        )







        Spacer(

            modifier = Modifier.size(24.dp)

        )







        ModeItem(

            title = "Simple",

            description = "Clean VPN experience",

            selected = mode == UserMode.SIMPLE,

            onClick = {

                onModeChange(

                    UserMode.SIMPLE

                )

            }

        )







        ModeItem(

            title = "Advanced",

            description = "Servers and network metrics",

            selected = mode == UserMode.ADVANCED,

            onClick = {

                onModeChange(

                    UserMode.ADVANCED

                )

            }

        )







        ModeItem(

            title = "Expert",

            description = "Diagnostics and engine information",

            selected = mode == UserMode.EXPERT,

            onClick = {

                onModeChange(

                    UserMode.EXPERT

                )

            }

        )



    }



}







@Composable
private fun ModeItem(


    title: String,


    description: String,


    selected: Boolean,


    onClick: () -> Unit


) {



    Column(


        modifier = Modifier

            .fillMaxWidth()

            .padding(vertical = 8.dp)

            .clickable {

                onClick()

            }


            .background(

                if(selected)

                    Color(0xFF162533)

                else

                    Color(0xFF111820)

            )

            .padding(20.dp)



    ) {



        Text(


            text =

                if(selected)

                    "● $title"

                else

                    "○ $title",



            color = Color.White,


            fontSize = 18.sp


        )





        Text(


            text = description,


            color = Color(0xFF9AA7B5),


            fontSize = 13.sp


        )


    }


}