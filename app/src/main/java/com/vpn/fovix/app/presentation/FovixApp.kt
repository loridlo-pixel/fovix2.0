package com.vpn.fovix.app.presentation


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


import com.vpn.fovix.app.AppContainer

import com.vpn.fovix.app.presentation.home.HomeScreenDynamic
import com.vpn.fovix.app.presentation.home.HomeViewModel
import com.vpn.fovix.app.presentation.home.HomeViewModelFactory

import com.vpn.fovix.app.presentation.navigation.FovixBottomBar
import com.vpn.fovix.app.presentation.navigation.FovixTab
import com.vpn.fovix.app.presentation.navigation.FovixTopBar

import com.vpn.fovix.app.presentation.settings.SettingsScreen
import com.vpn.fovix.app.presentation.settings.SettingsViewModel
import com.vpn.fovix.app.presentation.settings.SettingsViewModelFactory

import com.vpn.fovix.app.presentation.subscription.SubscriptionScreen
import com.vpn.fovix.app.presentation.subscription.SubscriptionViewModel
import com.vpn.fovix.app.presentation.subscription.SubscriptionViewModelFactory

import com.vpn.fovix.data.repository.VpnRepository

import com.vpn.fovix.domain.vpnstate.ConnectionStatus



@Composable
fun FovixApp(

    repository: VpnRepository,

    container: AppContainer,

    onConnect: () -> Unit,

    onDisconnect: () -> Unit

) {


    val selectedTab = remember {

        mutableStateOf(
            FovixTab.HOME
        )

    }


    /*
       Пока подключаем реальный статус из VPN слоя.
       После этого заменим на StateFlow из ViewModel.
    */

    val status = ConnectionStatus.DISCONNECTED



    Scaffold(


        topBar = {


            FovixTopBar(

                onAddClick = {

                    selectedTab.value =
                        FovixTab.SERVERS

                },


                onSettingsClick = {

                    selectedTab.value =
                        FovixTab.SETTINGS

                },


                onLogoClick = {

                    selectedTab.value =
                        FovixTab.HOME

                }

            )


        },



        bottomBar = {


            FovixBottomBar(

                selected = selectedTab.value,


                status = status,


                onSelect = {

                    selectedTab.value = it

                },


                onCoreClick = {


                    when(status) {


                        ConnectionStatus.CONNECTED -> {

                            onDisconnect()

                        }


                        else -> {

                            onConnect()

                        }


                    }


                }

            )


        }


    ) { padding ->



        Box(

            modifier = Modifier.padding(padding)

        ) {



            when(selectedTab.value) {



                FovixTab.HOME -> {


                    val vm: HomeViewModel = viewModel(

                        factory = HomeViewModelFactory(

                            repository,

                            container.userPreferences

                        )

                    )


                    val state by vm.state.collectAsState()



                    HomeScreenDynamic(

                        state = state,

                        onConnect = onConnect,

                        onDisconnect = onDisconnect,

                        onOpenSubscriptions = {

                            selectedTab.value =
                                FovixTab.SERVERS

                        }

                    )


                }




                FovixTab.SERVERS -> {


                    val vm: SubscriptionViewModel = viewModel(

                        factory = SubscriptionViewModelFactory(

                            container.subscriptionImportEngine,

                            container.serverRepository

                        )

                    )


                    val state by vm.state.collectAsState()



                    SubscriptionScreen(

                        state = state,


                        onInputChange = {

                            vm.updateInput(it)

                        },


                        onImport = {

                            vm.importSubscription()

                        },


                        onBack = {

                            selectedTab.value =
                                FovixTab.HOME

                        }

                    )


                }




                FovixTab.DOCTOR -> {


                    SettingsScreenPlaceholder(

                        "Network Doctor"

                    )


                }




                FovixTab.SETTINGS -> {


                    val vm: SettingsViewModel = viewModel(

                        factory = SettingsViewModelFactory(

                            container.userPreferences

                        )

                    )


                    val mode by vm.userMode.collectAsState()



                    SettingsScreen(

                        mode = mode,


                        onModeChange = {

                            vm.setMode(it)

                        },


                        onBack = {

                            selectedTab.value =
                                FovixTab.HOME

                        }

                    )


                }



            }


        }


    }


}



@Composable
private fun SettingsScreenPlaceholder(

    text: String

) {


    androidx.compose.material3.Text(

        text = text

    )


}