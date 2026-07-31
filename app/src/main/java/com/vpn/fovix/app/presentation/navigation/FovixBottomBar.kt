package com.vpn.fovix.app.presentation.navigation


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
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
                Color(0xFF0B1015)
            )

    ) {



        NavigationBar(

            containerColor = Color(0xFF0B1015)

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

                        contentDescription = "Servers"

                    )

                },

                label = {

                    Text(
                        "Servers"
                    )

                }

            )



            Spacer(

                modifier = Modifier.weight(1f)

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

                        contentDescription = "Doctor"

                    )

                },

                label = {

                    Text(
                        "Doctor"
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
                )

                .clickable {

                    onCoreClick()

                },


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