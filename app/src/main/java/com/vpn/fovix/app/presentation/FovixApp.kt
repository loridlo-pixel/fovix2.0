package com.vpn.fovix.app.presentation


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

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

import com.vpn.fovix.app.presentation.servers.ServersScreen

import com.vpn.fovix.app.presentation.theme.FovixBackground

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



    val status =
        ConnectionStatus.DISCONNECTED





    FovixBackground {



        Scaffold(


            containerColor = Color.Transparent,



            topBar = {



                FovixTopBar(


                    onAddSubscription = {


                        selectedTab.value =
                            FovixTab.SERVERS


                    },



                    onPasteClipboard = {


                        selectedTab.value =
                            FovixTab.SERVERS


                    },



                    onQrScan = {


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


                        when(status){


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



        ){ padding ->





            Box(

                modifier = Modifier

                    .fillMaxSize()

                    .padding(padding)

            ){



                when(selectedTab.value){



                    FovixTab.HOME -> {



                        val vm: HomeViewModel =
                            viewModel(


                                factory =
                                    HomeViewModelFactory(


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


                            },



                            onOpenServers = {


                                selectedTab.value =
                                    FovixTab.SERVERS


                            }


                        )



                    }






                    FovixTab.SERVERS -> {



                        val homeVm: HomeViewModel =
                            viewModel(


                                factory =
                                    HomeViewModelFactory(


                                        repository,


                                        container.userPreferences


                                    )

                            )



                        val homeState by homeVm.state.collectAsState()



                        ServersScreen(


                            selectedServer =
                                homeState.server,



                            onServerSelected = {


                                homeVm.selectServer(it)



                                selectedTab.value =
                                    FovixTab.HOME


                            },



                            onBack = {


                                selectedTab.value =
                                    FovixTab.HOME


                            }



                        )


                    }







                    FovixTab.DOCTOR -> {


                        PlaceholderScreen(

                            "Network Doctor"

                        )


                    }








                    FovixTab.SETTINGS -> {



                        val vm: SettingsViewModel =
                            viewModel(


                                factory =
                                    SettingsViewModelFactory(


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



}







@Composable
private fun PlaceholderScreen(

    text: String

){


    androidx.compose.material3.Text(


        text = text,


        color = Color.White


    )


}