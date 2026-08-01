package com.vpn.fovix.app.presentation.home


import androidx.compose.runtime.Composable



@Composable
fun HomeModeRenderer(


    state: HomeUiState,


    onConnect: () -> Unit,


    onDisconnect: () -> Unit,


    onOpenSubscriptions: () -> Unit,


    onOpenServers: () -> Unit


) {



    when(state.userMode) {



        UserMode.SIMPLE -> {



            SimpleHomeView(


                state = state,


                onConnect = onConnect,


                onDisconnect = onDisconnect,


                onOpenSubscriptions = onOpenSubscriptions


            )

        }






        UserMode.ADVANCED -> {



            AdvancedHomeView(


                state = state,


                onConnect = onConnect,


                onDisconnect = onDisconnect,


                onOpenSubscriptions = onOpenSubscriptions,


                onOpenServers = onOpenServers


            )

        }






        UserMode.EXPERT -> {



            ExpertHomeView(


                state = state,


                onConnect = onConnect,


                onDisconnect = onDisconnect,


                onOpenSubscriptions = onOpenSubscriptions,


                onOpenServers = onOpenServers


            )

        }


    }



}