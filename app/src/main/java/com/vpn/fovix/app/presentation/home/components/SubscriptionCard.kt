package com.vpn.fovix.app.presentation.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import com.vpn.fovix.domain.subscription.VpnSubscription



@Composable
fun SubscriptionCard(


    subscription: VpnSubscription?,


    onAddClick: () -> Unit


) {



    Column(


        modifier = Modifier

            .fillMaxWidth()

            .shadow(

                8.dp,

                RoundedCornerShape(24.dp)

            )

            .background(

                Color.White,

                RoundedCornerShape(24.dp)

            )

            .border(

                1.dp,

                Color(0xFFE6EAF0),

                RoundedCornerShape(24.dp)

            )

            .padding(20.dp)


    ) {



        Row(

            modifier =
                Modifier.fillMaxWidth(),


            horizontalArrangement =
                Arrangement.SpaceBetween,


            verticalAlignment =
                Alignment.CenterVertically

        ){



            Column {



                Text(

                    text =
                    subscription?.name
                        ?: "VPN Subscription",


                    fontSize =
                    18.sp,


                    color =
                    Color(0xFF111827)

                )



                Spacer(
                    Modifier.height(6.dp)
                )



                Text(

                    text =
                    if(subscription == null)

                        "Add provider subscription"

                    else

                        "${subscription.serversCount} servers available",


                    fontSize =
                    13.sp,


                    color =
                    Color(0xFF64748B)

                )


            }





            IconButton(

                onClick = onAddClick

            ){


                Icon(

                    Icons.Default.Add,

                    contentDescription = "Add"

                )


            }



        }





        Spacer(
            Modifier.height(20.dp)
        )




        if(subscription == null){



            Text(

                text =
                "No VPN subscription added",


                color =
                Color(0xFF94A3B8),


                fontSize =
                14.sp

            )



        }

        else {



            Row(

                modifier =
                Modifier.fillMaxWidth(),


                horizontalArrangement =
                Arrangement.SpaceBetween


            ){



                Text(

                    text =
                    "Servers",


                    color =
                    Color(0xFF94A3B8)

                )



                Text(

                    text =
                    subscription.serversCount.toString(),


                    color =
                    Color(0xFF111827)

                )


            }



        }




    }


}