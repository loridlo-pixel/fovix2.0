package com.vpn.fovix.app.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun VyryxTopBar(

    onSettingsClick: () -> Unit

) {

    Surface(

        color = Color.White

    ) {

        Box(

            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 12.dp,
                    bottom = 8.dp
                )

        ) {

            IconButton(

                modifier = Modifier
                    .align(Alignment.CenterEnd),

                onClick = onSettingsClick

            ) {

                Text(

                    text = "⚙",

                    fontSize = MaterialTheme.typography.titleLarge.fontSize,

                    color = Color(0xFF111827)

                )

            }

        }

    }

}