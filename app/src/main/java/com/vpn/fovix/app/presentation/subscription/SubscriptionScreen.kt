package com.vpn.fovix.app.presentation.subscription


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun SubscriptionScreen(

    state: SubscriptionUiState,

    onInputChange: (String) -> Unit,

    onImport: () -> Unit

) {


    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.Center

    ) {



        Text(

            text = "Import Subscription",

            style = MaterialTheme.typography.headlineSmall

        )





        Spacer(

            modifier = Modifier.height(24.dp)

        )





        OutlinedTextField(

            value = state.input,

            onValueChange = {

                onInputChange(it)

            },

            modifier = Modifier.fillMaxWidth(),

            label = {

                Text(
                    "URL / Base64 / Config"
                )

            }

        )





        Spacer(

            modifier = Modifier.height(16.dp)

        )





        Button(

            onClick = onImport,

            modifier = Modifier.fillMaxWidth(),

            enabled = !state.loading

        ) {


            Text(

                if(state.loading)

                    "Importing..."

                else

                    "Import"

            )


        }





        Spacer(

            modifier = Modifier.height(16.dp)

        )





        if(state.message.isNotEmpty()) {


            Text(

                text = state.message

            )


        }





        if(state.servers.isNotEmpty()) {


            Spacer(

                modifier = Modifier.height(20.dp)

            )


            Text(

                text =
                    "Servers: ${state.servers.size}"

            )


        }



    }


}