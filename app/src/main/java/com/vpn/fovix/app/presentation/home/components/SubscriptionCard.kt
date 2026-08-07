package com.vpn.fovix.app.presentation.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.vpn.fovix.domain.subscription.VpnSubscription



@Composable
fun SubscriptionCard(

    subscription: VpnSubscription?,

    onAddClick: () -> Unit,

    onMenuClick: () -> Unit = {}

) {


    val hasSubscription =
        subscription != null



    val background =

        if (hasSubscription)

            Brush.linearGradient(

                colors = listOf(

                    Color(0xFF38BDF8),

                    Color(0xFF0284C7)

                )

            )

        else

            Brush.linearGradient(

                colors = listOf(

                    Color(0xFFE9EEF3),

                    Color(0xFFDDE3EA)

                )

            )





    Column(

        modifier = Modifier

            .fillMaxWidth()

            .shadow(

                elevation = 10.dp,

                shape = RoundedCornerShape(26.dp)

            )

            .background(

                brush = background,

                shape = RoundedCornerShape(26.dp)

            )

            .padding(22.dp)

    ) {



        Row(

            modifier = Modifier

                .fillMaxWidth()

                .clickable {

                    if (!hasSubscription) {

                        onAddClick()

                    }

                },


            horizontalArrangement = Arrangement.SpaceBetween,

            verticalAlignment = Alignment.CenterVertically

        ) {



            Column(

                modifier = Modifier.weight(1f)

            ) {



                Text(

                    text =

                        if (hasSubscription)

                            subscription!!.name

                        else

                            "Добавьте вашу подписку",


                    color =

                        if (hasSubscription)

                            Color.White

                        else

                            Color(0xFF334155),


                    fontSize = 22.sp

                )





                Spacer(

                    modifier = Modifier.height(6.dp)

                )





                Text(

                    text =

                        if (hasSubscription)

                            "${subscription!!.servers.size} серверов доступно"

                        else

                            "Подключите VPN-сервис или импортируйте ссылку",


                    color =

                        if (hasSubscription)

                            Color.White.copy(alpha = 0.85f)

                        else

                            Color(0xFF64748B),


                    fontSize = 14.sp

                )


            }





            if (hasSubscription) {


                IconButton(

                    onClick = onMenuClick

                ) {


                    Icon(

                        imageVector = Icons.Default.MoreVert,

                        contentDescription = "Menu",

                        tint = Color.White,

                        modifier = Modifier.size(30.dp)

                    )

                }



            } else {



                IconButton(

                    onClick = onAddClick,

                    modifier = Modifier

                        .size(48.dp)

                        .background(

                            Color.White.copy(alpha = 0.75f),

                            CircleShape

                        )

                ) {


                    Text(

                        text = "+",

                        color = Color(0xFF334155),

                        fontSize = 30.sp

                    )


                }


            }



        }





        if (hasSubscription) {



            Spacer(

                modifier = Modifier.height(18.dp)

            )





            subscription!!.servers

                .take(5)

                .forEach { server ->



                    Row(

                        modifier = Modifier

                            .fillMaxWidth()

                            .padding(vertical = 6.dp),


                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {



                        Text(

                            text = server.name,

                            color = Color.White,

                            fontSize = 14.sp

                        )





                        Text(

                            text = "— ms",

                            color = Color.White.copy(alpha = 0.8f),

                            fontSize = 13.sp

                        )


                    }


                }





            if (subscription!!.servers.size > 5) {


                Spacer(

                    modifier = Modifier.height(8.dp)

                )





                Text(

                    text = "+${subscription!!.servers.size - 5} ещё",

                    color = Color.White.copy(alpha = 0.8f),

                    fontSize = 13.sp

                )


            }


        }


    }


}