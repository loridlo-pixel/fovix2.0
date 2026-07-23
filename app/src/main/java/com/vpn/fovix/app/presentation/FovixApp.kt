package com.vpn.fovix.app.presentation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel


import com.vpn.fovix.app.presentation.home.HomeScreenDynamic
import com.vpn.fovix.app.presentation.home.HomeViewModel
import com.vpn.fovix.app.presentation.home.HomeViewModelFactory
import com.vpn.fovix.app.presentation.home.toHomeUiState


import com.vpn.fovix.app.presentation.subscription.SubscriptionScreen
import com.vpn.fovix.app.presentation.subscription.SubscriptionViewModel
import com.vpn.fovix.app.presentation.subscription.SubscriptionViewModelFactory


import com.vpn.fovix.data.importer.SubscriptionImportEngine
import com.vpn.fovix.data.repository.ServerRepository
import com.vpn.fovix.data.repository.VpnRepository




@Composable
fun FovixApp(

    repository: VpnRepository,

    serverRepository: ServerRepository,

    subscriptionImportEngine: SubscriptionImportEngine,

    onConnect: () -> Unit,

    onDisconnect: () -> Unit

) {



    val showSubscription = remember {

        mutableStateOf(false)

    }






    if(showSubscription.value) {



        val subscriptionViewModel: SubscriptionViewModel =

            viewModel(

                factory = SubscriptionViewModelFactory(

                    subscriptionImportEngine,

                    serverRepository

                )

            )



        val state by subscriptionViewModel.state.collectAsState()





        SubscriptionScreen(

            state = state,


            onInputChange = {


                subscriptionViewModel.updateInput(

                    it

                )


            },


            onImport = {


                subscriptionViewModel.importSubscription()


            }


        )



    }
    else {



        val homeViewModel: HomeViewModel =

            viewModel(

                factory = HomeViewModelFactory(

                    repository

                )

            )





        val vpnState by homeViewModel.state.collectAsState()



        val homeState =

            vpnState.toHomeUiState()





        HomeScreenDynamic(

            state = homeState,


            onConnect = onConnect,


            onDisconnect = onDisconnect,


            onOpenSubscriptions = {


                showSubscription.value = true


            }

        )


    }



}