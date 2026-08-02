package com.vpn.fovix.app.presentation.home


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp


import com.vpn.fovix.app.presentation.home.components.VyryxCoreCard
import com.vpn.fovix.app.presentation.home.components.VyryxHeader
import com.vpn.fovix.app.presentation.home.components.VyryxHomeLayout



@Composable
fun SimpleHomeView(

    state: HomeUiState,

    onConnect: () -> Unit,

    onDisconnect: () -> Unit,

    onOpenSubscriptions: () -> Unit

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


            Text(

                text = "Personal Internet Security",

                color = Color(0xFF6B7280),

                fontSize = 14.sp

            )


        }


    )

}