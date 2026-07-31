package com.vpn.fovix.app.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.vpn.fovix.domain.vpnstate.ConnectionStatus


enum class FovixTab {

    DOCTOR,

    STATS

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

                selected = selected == FovixTab.DOCTOR,

                onClick = {

                    onSelect(
                        FovixTab.DOCTOR
                    )

                },

                icon = {

                    Icon(

                        imageVector = Icons.Default.Build,

                        contentDescription = "Doctor"

                    )

                },

                label = {

                    Text("Doctor")

                }

            )



            Spacer(

                modifier = Modifier.weight(1f)

            )



            NavigationBarItem(

                selected = selected == FovixTab.STATS,

                onClick = {

                    onSelect(
                        FovixTab.STATS
                    )

                },

                icon = {

                    Icon(

                        imageVector = Icons.Default.Info,

                        contentDescription = "Statistics"

                    )

                },

                label = {

                    Text("Stats")

                }

            )


        }



        /*
            Центральное место под FOVIX Core.

            Сейчас оставляем пустым,
            чтобы не ломать сборку.
            После подключения Scaffold
            сюда вернём настоящий FovixCore.
        */


        Box(

            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(64.dp)

        )


    }

}