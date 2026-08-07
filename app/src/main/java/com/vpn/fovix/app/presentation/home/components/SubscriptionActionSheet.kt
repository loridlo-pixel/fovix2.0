package com.vpn.fovix.app.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscriptionActionSheet(

    url: String,

    loading: Boolean,

    onDismiss: () -> Unit,

    onUrlChange: (String) -> Unit,

    onImport: () -> Unit,

    onPaste: () -> Unit,

    onQr: () -> Unit,

    error: String? = null

) {


    ModalBottomSheet(

        onDismissRequest = onDismiss

    ) {


        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)

        ) {



            Text(

                text = "Добавить подписку",

                style = MaterialTheme.typography.titleLarge

            )



            Spacer(
                Modifier.height(16.dp)
            )



            OutlinedTextField(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),

                value = url,

                onValueChange = onUrlChange,


                placeholder = {

                    Text(
                        "happ://...\nили ссылка подписки"
                    )

                },


                maxLines = 5

            )



            Spacer(
                Modifier.height(16.dp)
            )




            Button(

                modifier = Modifier
                    .fillMaxWidth(),

                enabled =

                    url.isNotBlank()
                    &&
                    !loading,


                onClick = onImport


            ) {


                if(loading) {


                    CircularProgressIndicator(

                        modifier = Modifier.size(20.dp),

                        color = MaterialTheme.colorScheme.onPrimary

                    )


                } else {


                    Text(
                        "Импортировать"
                    )


                }


            }




            Spacer(
                Modifier.height(12.dp)
            )




            OutlinedButton(

                modifier = Modifier
                    .fillMaxWidth(),

                onClick = onPaste

            ) {


                Text(
                    "Вставить из буфера"
                )


            }





            Spacer(
                Modifier.height(12.dp)
            )




            OutlinedButton(

                modifier = Modifier
                    .fillMaxWidth(),

                onClick = onQr

            ) {


                Text(
                    "QR код"
                )


            }





            error?.let {


                Spacer(
                    Modifier.height(12.dp)
                )


                Text(

                    text = it,

                    color = MaterialTheme.colorScheme.error

                )


            }



            Spacer(
                Modifier.height(24.dp)
            )

        }


    }

}