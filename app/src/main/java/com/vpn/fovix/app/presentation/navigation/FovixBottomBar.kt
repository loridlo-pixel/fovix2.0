package com.vpn.fovix.app.presentation.navigation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp

import com.vpn.fovix.app.presentation.core.FovixCore

import com.vpn.fovix.domain.vpnstate.ConnectionStatus



enum class FovixTab {


    HOME,

    SERVERS,

    DIAGNOSTICS,

    STATS,

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


        modifier = Modifier.fillMaxWidth()


    ) {



        NavigationBar(


            containerColor = Color(0xFF0B1015)


        ) {



            NavigationBarItem(


                selected = selected == FovixTab.HOME,


                onClick = {


                    onSelect(

                        FovixTab.HOME

                    )


                },


                icon = {


                    Icon(

                        Icons.Default.Home,

                        contentDescription = null

                    )


                },


                label = {


                    Text(

                        "Home"

                    )


                }


            )






            NavigationBarItem(


                selected = selected == FovixTab.SERVERS,


                onClick = {


                    onSelect(

                        FovixTab.SERVERS

                    )


                },


                icon = {


                    Icon(

                        Icons.Default.List,

                        contentDescription = null

                    )


                },


                label = {


                    Text(

                        "Servers"

                    )


                }


            )






            Spacer(

                modifier = Modifier.size(64.dp)

            )






            NavigationBarItem(


                selected = selected == FovixTab.DIAGNOSTICS,


                onClick = {


                    onSelect(

                        FovixTab.DIAGNOSTICS

                    )


                },


                icon = {


                    Icon(

                        Icons.Default.Build,

                        contentDescription = null

                    )


                },


                label = {


                    Text(

                        "Doctor"

                    )


                }


            )






            NavigationBarItem(


                selected = selected == FovixTab.SETTINGS,


                onClick = {


                    onSelect(

                        FovixTab.SETTINGS

                    )


                },


                icon = {


                    Icon(

                        Icons.Default.Settings,

                        contentDescription = null

                    )


                },


                label = {


                    Text(

                        "Settings"

                    )


                }


            )



        }







        Box(


            modifier = Modifier

                .align(

                    Alignment.TopCenter

                )

                .size(64.dp)

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