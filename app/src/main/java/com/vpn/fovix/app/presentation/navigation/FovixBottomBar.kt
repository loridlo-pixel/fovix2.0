package com.vpn.fovix.app.presentation.navigation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp


import com.composables.icons.lucide.Globe
import com.composables.icons.lucide.Stethoscope
import com.composables.icons.lucide.Lucide


import com.vpn.fovix.app.presentation.core.FovixCore

import com.vpn.fovix.domain.vpnstate.ConnectionStatus





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

            .background(

                Color.Transparent

            )



    ) {



        NavigationBar(


            containerColor = Color.Transparent,


            tonalElevation = 0.dp



        ) {



            NavigationBarItem(


                selected = selected == FovixTab.SERVERS,


                onClick = {


                    onSelect(

                        FovixTab.SERVERS

                    )


                },


                icon = {


                    Icon(

                        imageVector = Lucide.Globe,

                        contentDescription = "Servers",

                        tint = Color.White

                    )


                },


                label = {


                    Text(

                        "Servers",

                        color = Color.White

                    )


                }


            )





            NavigationBarItem(


                selected = selected == FovixTab.DOCTOR,


                onClick = {


                    onSelect(

                        FovixTab.DOCTOR

                    )


                },


                icon = {


                    Icon(

                        imageVector = Lucide.Stethoscope,

                        contentDescription = "Doctor",

                        tint = Color.White

                    )


                },


                label = {


                    Text(

                        "Doctor",

                        color = Color.White

                    )


                }


            )



        }






        Box(


            modifier = Modifier

                .align(

                    Alignment.TopCenter

                )

                .size(

                    78.dp

                )


                .background(

                    Color.Transparent,

                    CircleShape

                ),


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