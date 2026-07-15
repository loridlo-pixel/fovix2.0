package com.vpn.fovix.app.presentation.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vpn.fovix.app.presentation.home.components.MetricsCard
import com.vpn.fovix.app.presentation.home.components.ServerCard
import com.vpn.fovix.app.presentation.home.components.StatusCard



@Composable
fun HomeScreenDynamic(

    state: HomeUiState,

    onConnect: () -> Unit,

    onDisconnect: () -> Unit

) {



    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {



        Text(

            text = "FOVIX"

        )



        Spacer(

            modifier = Modifier.height(32.dp)

        )



        ConnectionOrb(

            connected = state.connected,

            onClick = {


                if(state.connected) {

                    onDisconnect()

                }
                else {

                    onConnect()

                }


            }

        )



        Spacer(

            modifier = Modifier.height(24.dp)

        )



        StatusCard(

            status = state.status.name

        )



        Spacer(

            modifier = Modifier.height(16.dp)

        )



        if(state.showServer) {


            ServerCard(

                server = state.server

            )


        }



        Spacer(

            modifier = Modifier.height(16.dp)

        )



        if(state.showMetrics) {


            MetricsCard(

                ping = "Ping 40 ms"

            )


        }



        if(state.showExpert) {


            Spacer(

                modifier = Modifier.height(16.dp)

            )


            Text(

                text = "Expert tools enabled"

            )

        }


    }


}