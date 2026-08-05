package com.vpn.fovix.app.presentation.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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

    onAddClick: () -> Unit

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

                    Color(0xFFE5E7EB),

                    Color(0xFFD1D5DB)

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

            .clickable {

                onAddClick()

            }

            .padding(22.dp)

    ) {



        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween,

            verticalAlignment = Alignment.CenterVertically

        ) {



            Column {



                Text(

                    text =

                        subscription?.name

                            ?: "VPN Provider",


                    color =

                        if (hasSubscription)

                            Color.White

                        else

                            Color(0xFF334155),


                    fontSize = 20.sp

                )





                Spacer(

                    modifier = Modifier.height(6.dp)

                )





                Text(

                    text =

                        if (hasSubscription)

                            "${subscription?.servers?.size ?: 0} servers available"

                        else

                            "Add subscription to activate protection",


                    color =

                        if (hasSubscription)

                            Color.White.copy(alpha = 0.85f)

                        else

                            Color(0xFF64748B),


                    fontSize = 14.sp

                )


            }







            IconButton(

                onClick = onAddClick

            ) {



                Icon(

                    imageVector = Icons.Default.Add,

                    contentDescription = "Add subscription",

                    tint =

                        if (hasSubscription)

                            Color.White

                        else

                            Color(0xFF475569)

                )


            }


        }





        Spacer(

            modifier = Modifier.height(18.dp)

        )






        if (subscription == null) {



            Text(

                text = "No VPN subscription added",

                color = Color(0xFF64748B),

                fontSize = 14.sp

            )



        } else {



            subscription.servers

                .take(5)

                .forEach { server ->



                    Row(

                        modifier = Modifier

                            .fillMaxWidth()

                            .padding(

                                vertical = 6.dp

                            ),


                        horizontalArrangement =

                            Arrangement.SpaceBetween,


                        verticalAlignment =

                            Alignment.CenterVertically

                    ) {



                        Text(

                            text = server.name,

                            color = Color.White,

                            fontSize = 14.sp

                        )





                        Text(

                            text = "Available",

                            color = Color.White.copy(

                                alpha = 0.85f

                            ),

                            fontSize = 13.sp

                        )



                    }



                }







            if ((subscription.servers.size) > 5) {



                Spacer(

                    modifier = Modifier.height(8.dp)

                )





                Text(

                    text =

                        "+${subscription.servers.size - 5} more servers",


                    color = Color.White.copy(

                        alpha = 0.8f

                    ),

                    fontSize = 13.sp

                )



            }



        }



    }



}