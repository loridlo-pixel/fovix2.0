package com.vpn.fovix.app.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun VyryxHomeLayout(

    header: @Composable () -> Unit,

    core: @Composable () -> Unit,

    content: @Composable () -> Unit

) {


    Column(

        modifier = Modifier

            .fillMaxSize()

            .verticalScroll(

                rememberScrollState()

            )

            .padding(

                horizontal = 20.dp,

                vertical = 16.dp

            ),


        horizontalAlignment = Alignment.CenterHorizontally,


        verticalArrangement = Arrangement.Top

    ) {


        header()


        Spacer(

            modifier = Modifier.height(24.dp)

        )


        core()


        Spacer(

            modifier = Modifier.height(20.dp)

        )


        content()


    }

}