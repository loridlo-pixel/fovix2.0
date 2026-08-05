package com.vpn.fovix.app.presentation.subscription


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun SubscriptionScreen(

    state: SubscriptionUiState,

    onUrlChange: (String) -> Unit,

    onImport: () -> Unit,

    onBack: () -> Unit

) {


    Column(

        modifier = Modifier

            .fillMaxSize()

            .background(
                Color(0xFFF5F7FA)
            )

            .padding(20.dp)

    ) {



        Row(

            modifier = Modifier
                .fillMaxWidth(),

            verticalAlignment =
                Alignment.CenterVertically

        ) {


            IconButton(

                onClick = onBack

            ) {


                Icon(

                    imageVector =
                        Icons.Default.ArrowBack,

                    contentDescription =
                        "Back"

                )


            }



            Text(

                text = "Subscriptions",

                fontSize = 24.sp,

                color =
                    Color(0xFF111827)

            )


        }





        Spacer(
            Modifier.height(24.dp)
        )





        Card(

            modifier = Modifier

                .fillMaxWidth()

                .shadow(
                    8.dp,
                    RoundedCornerShape(24.dp)
                ),

            shape =
                RoundedCornerShape(24.dp),

            colors =
                CardDefaults.cardColors(

                    containerColor =
                        Color.White

                )

        ) {



            Column(

                modifier =
                    Modifier.padding(20.dp)

            ) {



                Text(

                    text =
                        "Add VPN provider",

                    fontSize =
                        18.sp,

                    color =
                        Color(0xFF111827)

                )



                Spacer(
                    Modifier.height(12.dp)
                )




                OutlinedTextField(

                    modifier =
                        Modifier.fillMaxWidth(),

                    value =
                        state.url,

                    onValueChange =
                        onUrlChange,


                    placeholder = {

                        Text(
                            "Subscription URL"
                        )

                    },


                    singleLine = true


                )





                Spacer(
                    Modifier.height(16.dp)
                )





                Button(

                    modifier =
                        Modifier.fillMaxWidth(),


                    enabled =
                        state.url.isNotBlank()
                            &&
                        !state.isLoading,


                    onClick =
                        onImport


                ) {


                    if(state.isLoading) {


                        CircularProgressIndicator(

                            modifier =
                                Modifier.size(20.dp),

                            color =
                                Color.White

                        )


                    }

                    else {


                        Text(
                            "Import subscription"
                        )


                    }


                }



                state.error?.let {


                    Spacer(
                        Modifier.height(12.dp)
                    )


                    Text(

                        text = it,

                        color =
                            Color.Red

                    )


                }



            }



        }






        Spacer(
            Modifier.height(20.dp)
        )





        Text(

            text =
                "Your providers",

            fontSize =
                18.sp,

            color =
                Color(0xFF111827)

        )





        Spacer(
            Modifier.height(12.dp)
        )






        if(
            state.subscriptions.isEmpty()
        ) {



            Card(

                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(20.dp),

                colors =
                    CardDefaults.cardColors(

                        containerColor =
                            Color.White

                    )

            ) {


                Text(

                    modifier =
                        Modifier.padding(20.dp),


                    text =
                        "No subscriptions added yet",

                    color =
                        Color(0xFF64748B)

                )


            }


        }

        else {



            Column(

                verticalArrangement =
                    Arrangement.spacedBy(12.dp)

            ) {



                state.subscriptions.forEach { subscription ->



                    SubscriptionItemCard(

                        subscription =
                            subscription,


                        selected =
                            state.selected?.id
                                ==
                            subscription.id

                    )


                }


            }


        }



    }


}







@Composable
private fun SubscriptionItemCard(

    subscription: VpnSubscription,

    selected: Boolean

) {



    Card(

        modifier =
            Modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(20.dp),


        colors =
            CardDefaults.cardColors(

                containerColor =
                    if(selected)

                        Color(0xFFE8F7FF)

                    else

                        Color.White

            )

    ) {



        Column(

            modifier =
                Modifier.padding(18.dp)

        ) {



            Text(

                text =
                    subscription.name,

                fontSize =
                    17.sp,

                color =
                    Color(0xFF111827)

            )



            Spacer(
                Modifier.height(8.dp)
            )



            Text(

                text =
                    subscription.url,

                fontSize =
                    13.sp,

                color =
                    Color(0xFF64748B)

            )



            Spacer(
                Modifier.height(8.dp)
            )



            Text(

                text =
                    "${subscription.servers.size} servers",

                fontSize =
                    14.sp,

                color =
                    Color(0xFF0284C7)

            )



        }


    }


}