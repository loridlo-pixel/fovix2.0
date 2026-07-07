package com.vpn.fovix.app.presentation.home


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.vpn.fovix.app.presentation.home.components.MetricsCard
import com.vpn.fovix.app.presentation.home.components.ServerCard
import com.vpn.fovix.app.presentation.home.components.StatusCard



@Composable
fun HomeScreenDynamic(

    state: HomeUiState

){


    Column {


        StatusCard(
            status = state.status.name
        )



        if(state.showServer){

            ServerCard(
                server = "Auto Server"
            )

        }



        if(state.showMetrics){

            MetricsCard(
                ping = "Ping 40 ms"
            )

        }



        if(state.showExpert){

            Text(
                text = "Expert tools enabled"
            )

        }


    }


}
