package com.vpn.fovix.app.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Dns
import androidx.compose.material.icons.filled.Settings

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.vpn.fovix.app.presentation.core.FovixCore
import com.vpn.fovix.domain.vpnstate.ConnectionStatus



enum class FovixTab {

    SERVERS,

    DOCTOR,

    SETTINGS

}



@Composable
fun FovixBottomBar(

    selected: FovixTab,

    status: ConnectionStatus,

    onSelect: (FovixTab) -> Unit,

    onCoreClick: () -> Unit

) {


    Box(

        modifier = Modifier
            .fillMaxWidth()
            .height(82.dp)
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF101820),
                        Color(0xFF0B1015)
                    )
                )
            )

    ) {



        Row(

            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(70.dp),

            horizontalArrangement = Arrangement.SpaceAround,

            verticalAlignment = Alignment.CenterVertically

        ) {



            BottomItem(

                selected = selected == FovixTab.SERVERS,

                icon = {

                    Icon(
                        Icons.Default.Dns,
                        null,
                        tint = Color.White
                    )

                },

                text = "Servers",

                onClick = {

                    onSelect(FovixTab.SERVERS)

                }

            )




            Spacer(

                modifier = Modifier.size(70.dp)

            )




            BottomItem(

                selected = selected == FovixTab.DOCTOR,

                icon = {

                    Icon(
                        Icons.Default.Build,
                        null,
                        tint = Color.White
                    )

                },

                text = "Doctor",

                onClick = {

                    onSelect(FovixTab.DOCTOR)

                }

            )



            BottomItem(

                selected = selected == FovixTab.SETTINGS,

                icon = {

                    Icon(
                        Icons.Default.Settings,
                        null,
                        tint = Color.White
                    )

                },

                text = "Settings",

                onClick = {

                    onSelect(FovixTab.SETTINGS)

                }

            )



        }




        Box(

            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-24).dp)
                .size(72.dp),

            contentAlignment = Alignment.Center

        ) {


            FovixCore(

                status = status,

                compact = true,

                onClick = onCoreClick

            )


        }


    }


}




@Composable
private fun BottomItem(

    selected: Boolean,

    icon: @Composable () -> Unit,

    text: String,

    onClick: () -> Unit

) {


    IconButton(

        onClick = onClick,

        modifier = Modifier.size(70.dp)

    ) {


        Box(

            contentAlignment = Alignment.Center

        ) {


            androidx.compose.foundation.layout.Column(

                horizontalAlignment = Alignment.CenterHorizontally

            ) {


                icon()


                Text(

                    text = text,

                    color = if(selected)

                        Color(0xFF00E5FF)

                    else

                        Color.White.copy(alpha = 0.65f)

                )


            }


        }


    }


}