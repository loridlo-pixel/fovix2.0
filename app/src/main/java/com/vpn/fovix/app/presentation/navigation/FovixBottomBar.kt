package com.vpn.fovix.app.presentation.navigation


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.vpn.fovix.app.presentation.theme.FovixAccent
import com.vpn.fovix.app.presentation.theme.FovixBackground
import com.vpn.fovix.app.presentation.theme.FovixSurface


@Composable
fun FovixBottomBar(

    selected: FovixTab,

    onSelected: (FovixTab) -> Unit,

    onCoreClick: () -> Unit

) {


    Box(

        modifier = Modifier

            .fillMaxWidth()

            .background(FovixBackground)

            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            )

    ) {


        Row(

            modifier = Modifier

                .fillMaxWidth()

                .height(72.dp)

                .shadow(
                    20.dp,
                    RoundedCornerShape(28.dp)
                )

                .background(
                    FovixSurface,
                    RoundedCornerShape(28.dp)
                )

                .padding(horizontal = 10.dp),


            verticalAlignment = Alignment.CenterVertically,

            horizontalArrangement = Arrangement.SpaceAround


        ) {



            BottomItem(

                icon = "⌂",

                title = "Home",

                active = selected == FovixTab.HOME

            ) {

                onSelected(FovixTab.HOME)

            }




            BottomItem(

                icon = "◉",

                title = "Servers",

                active = selected == FovixTab.SERVERS

            ) {

                onSelected(FovixTab.SERVERS)

            }




            Box(

                modifier = Modifier

                    .size(62.dp)

                    .shadow(
                        18.dp,
                        CircleShape
                    )

                    .background(
                        FovixAccent,
                        CircleShape
                    )

                    .clickable {

                        onCoreClick()

                    },


                contentAlignment = Alignment.Center


            ) {


                Text(

                    text = "CORE",

                    color = Color.Black,

                    style = MaterialTheme.typography.labelMedium


                )


            }




            BottomItem(

                icon = "✚",

                title = "Doctor",

                active = selected == FovixTab.DOCTOR

            ) {

                onSelected(FovixTab.DOCTOR)

            }




            BottomItem(

                icon = "⚙",

                title = "Settings",

                active = selected == FovixTab.SETTINGS

            ) {

                onSelected(FovixTab.SETTINGS)

            }


        }

    }

}




@Composable
private fun BottomItem(

    icon: String,

    title: String,

    active: Boolean,

    onClick: () -> Unit

) {


    Column(

        modifier = Modifier

            .clickable {

                onClick()

            }

            .padding(horizontal = 8.dp),


        horizontalAlignment = Alignment.CenterHorizontally


    ) {


        Text(

            text = icon,

            color = if(active)

                FovixAccent

            else

                Color.Gray


        )


        Spacer(

            modifier = Modifier.height(4.dp)

        )


        Text(

            text = title,

            color = if(active)

                FovixAccent

            else

                Color.Gray,


            style = MaterialTheme.typography.labelSmall


        )


    }

}