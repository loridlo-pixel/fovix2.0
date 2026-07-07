package com.vpn.fovix.app.presentation.mode


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.vpn.fovix.domain.usermode.UserMode



@Composable
fun ModeSelector(

    current: UserMode,

    onChange:(UserMode)->Unit

){


    Column {


        Button(
            onClick = {

                onChange(UserMode.SIMPLE)

            }
        ){

            Text("Simple")

        }



        Button(
            onClick = {

                onChange(UserMode.ADVANCED)

            }
        ){

            Text("Advanced")

        }



        Button(
            onClick = {

                onChange(UserMode.EXPERT)

            }
        ){

            Text("Expert")

        }


    }


}
