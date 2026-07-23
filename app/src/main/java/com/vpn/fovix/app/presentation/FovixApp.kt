package com.vpn.fovix.app.presentation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.lifecycle.viewmodel.compose.viewModel


import com.vpn.fovix.app.AppContainer

import com.vpn.fovix.app.presentation.home.HomeScreenDynamic
import com.vpn.fovix.app.presentation.home.HomeViewModel
import com.vpn.fovix.app.presentation.home.HomeViewModelFactory

import com.vpn.fovix.app.presentation.settings.SettingsScreen
import com.vpn.fovix.app.presentation.settings.SettingsViewModel
import com.vpn.fovix.app.presentation.settings.SettingsViewModelFactory

import com.vpn.fovix.app.presentation.subscription.SubscriptionScreen
import com.vpn.fovix.app.presentation.subscription.SubscriptionViewModel
import com.vpn.fovix.app.presentation.subscription.SubscriptionViewModelFactory

import com.vpn.fovix.data.repository.VpnRepository



@Composable
fun FovixApp(


    repository: VpnRepository,


    container: AppContainer,


    onConnect: () -> Unit,


    onDisconnect: () -> Unit


) {



    val screen = remember {


        mutableStateOf(

            AppScreen.HOME

        )


    }






    when(screen.value) {



        AppScreen.HOME -> {



            val homeViewModel: HomeViewModel = viewModel(


                factory = HomeViewModelFactory(


                    repository,


                    container.userPreferences


                )


            )



            val homeState by homeViewModel.state.collectAsState()





            HomeScreenDynamic(


                state = homeState,


                onConnect = onConnect,


                onDisconnect = onDisconnect,


                onOpenSubscriptions = {


                    screen.value =

                        AppScreen.SUBSCRIPTIONS


                }


            )



        }







        AppScreen.SUBSCRIPTIONS -> {



            val subscriptionViewModel: SubscriptionViewModel = viewModel(


                factory = SubscriptionViewModelFactory(


                    container.subscriptionImportEngine,


                    container.serverRepository


                )


            )



            val subscriptionState by subscriptionViewModel.state.collectAsState()



            SubscriptionScreen(


                state = subscriptionState,


                onInputChange = {


                    subscriptionViewModel.updateInput(

                        it

                    )


                },


                onImport = {


                    subscriptionViewModel.importSubscription()


                },


                onBack = {


                    screen.value =

                        AppScreen.HOME


                }


            )



        }







        AppScreen.SETTINGS -> {



            val settingsViewModel: SettingsViewModel = viewModel(


                factory = SettingsViewModelFactory(


                    container.userPreferences


                )


            )



            val mode by settingsViewModel.userMode.collectAsState()



            SettingsScreen(


                mode = mode,


                onModeChange = {


                    settingsViewModel.setMode(

                        it

                    )


                },


                onBack = {


                    screen.value =

                        AppScreen.HOME


                }


            )



        }



    }



}