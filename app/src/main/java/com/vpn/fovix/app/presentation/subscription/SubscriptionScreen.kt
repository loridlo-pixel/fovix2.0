package com.vpn.fovix.app.presentation.subscription


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.vpn.fovix.app.presentation.subscription.components.SubscriptionImportButton
import com.vpn.fovix.app.presentation.subscription.components.SubscriptionInputCard




@Composable
fun SubscriptionScreen(


    state: SubscriptionUiState,


    onInputChange: (String) -> Unit,


    onImport: () -> Unit,


    onBack: () -> Unit


) {



    Column(


        modifier = Modifier

            .fillMaxSize()

            .background(Color.Transparent)

            .padding(24.dp),



        horizontalAlignment = Alignment.CenterHorizontally,


        verticalArrangement = Arrangement.Center


    ) {





        Row(


            modifier = Modifier

                .fillMaxWidth(),



            verticalAlignment = Alignment.CenterVertically


        ) {



            Text(


                text = "←",


                color = Color(0xFF00E5FF),


                fontSize = 32.sp,


                modifier = Modifier

                    .clickable {

                        onBack()

                    }

                    .padding(

                        end = 20.dp

                    )

            )




            Text(


                text = "FOVIX",


                color = Color.White,


                fontSize = 32.sp


            )



        }







        Spacer(

            modifier = Modifier.size(24.dp)

        )







        Text(


            text = "Subscription Import",


            color = Color(0xFF00E5FF),


            fontSize = 20.sp


        )





        Spacer(

            modifier = Modifier.size(8.dp)

        )






        Text(


            text = "Add your VPN source",


            color = Color(0xFF8B98A8),


            fontSize = 14.sp


        )






        Spacer(

            modifier = Modifier.size(32.dp)

        )






        SubscriptionInputCard(


            value = state.input,


            onValueChange = onInputChange


        )






        Spacer(

            modifier = Modifier.size(24.dp)

        )






        SubscriptionImportButton(


            enabled = state.input.isNotBlank(),


            onClick = onImport


        )






        Spacer(

            modifier = Modifier.size(32.dp)

        )






        Text(


            text = "Supported formats",


            color = Color.White,


            fontSize = 16.sp


        )






        Spacer(

            modifier = Modifier.size(12.dp)

        )







        Text(


            text =

                """
                ✓ VLESS
                ✓ VMess
                ✓ Trojan
                ✓ Base64
                ✓ Xray JSON
                """.trimIndent(),



            color = Color(0xFF9AA7B5),


            fontSize = 14.sp


        )



    }


}