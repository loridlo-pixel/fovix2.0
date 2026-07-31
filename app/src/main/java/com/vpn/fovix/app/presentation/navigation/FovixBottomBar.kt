package com.vpn.fovix.app.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
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

        modifier = Modifier.fillMaxWidth()

    ) {


        NavigationBar(

            containerColor = Color(0xFF0B1015)

        ) {


            NavigationBarItem(

                selected = selected == FovixTab.HOME,

                onClick = {
                    onSelect(FovixTab.HOME)
                },

                icon = {

                    Icon(
                        Icons.Default.Home,
                        null
                    )

                },

                label = {

                    Text("Home")

                }

            )



            NavigationBarItem(

                selected = selected == FovixTab.SERVERS,

                onClick = {
                    onSelect(FovixTab.SERVERS)
                },

                icon = {

                    Icon(
                        Icons.Default.List,
                        null
                    )

                },

                label = {

                    Text("Servers")

                }

            )



            Spacer(

                modifier = Modifier.weight(1f)

            )



            NavigationBarItem(

                selected = selected == FovixTab.DOCTOR,

                onClick = {
                    onSelect(FovixTab.DOCTOR)
                },

                icon = {

                    Icon(
                        Icons.Default.Build,
                        null
                    )

                },

                label = {

                    Text("Doctor")

                }

            )



            NavigationBarItem(

                selected = selected == FovixTab.SETTINGS,

                onClick = {
                    onSelect(FovixTab.SETTINGS)
                },

                icon = {

                    Icon(
                        Icons.Default.Settings,
                        null
                    )

                },

                label = {

                    Text("Settings")

                }

            )


        }



        Box(

            modifier = Modifier
                .align(Alignment.TopCenter)
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