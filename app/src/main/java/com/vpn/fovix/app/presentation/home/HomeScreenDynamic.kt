package com.vpn.fovix.app.presentation.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp



@Composable
fun HomeScreenDynamic(


    state: HomeUiState,


    onConnect: () -> Unit,


    onDisconnect: () -> Unit,


    onOpenSubscriptions: () -> Unit


) {



    Box(


        modifier = Modifier

            .fillMaxSize()

            .background(

                Brush.verticalGradient(

                    colors = listOf(

                        Color(0xFF0B1015),

                        Color(0xFF111820)

                    )

                )

            )

            .padding(24.dp),



        contentAlignment = Alignment.Center


    ) {



        HomeModeRenderer(


            state = state,


            onConnect = onConnect,


            onDisconnect = onDisconnect,


            onOpenSubscriptions = onOpenSubscriptions


        )



    }



}