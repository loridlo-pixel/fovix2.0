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
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier

import androidx.lifecycle.viewmodel.compose.viewModel

import com.vpn.fovix.app.presentation.home.HomeDashboard
import com.vpn.fovix.app.presentation.home.UserMode
import com.vpn.fovix.app.presentation.home.components.ProtectionScenario
import com.vpn.fovix.app.presentation.home.components.SubscriptionActionSheet

import com.vpn.fovix.app.presentation.navigation.FovixBottomBar
import com.vpn.fovix.app.presentation.navigation.FovixTab

import com.vpn.fovix.app.presentation.subscription.SubscriptionViewModel
import com.vpn.fovix.app.presentation.subscription.SubscriptionViewModelFactory

import com.vpn.fovix.data.importer.SubscriptionImportEngine
import com.vpn.fovix.data.repository.VpnRepository
import com.vpn.fovix.data.subscription.SubscriptionRepository


@Composable
fun FovixApp(

    vpnRepository: VpnRepository,

    subscriptionRepository: SubscriptionRepository,

    subscriptionImportEngine: SubscriptionImportEngine

) {


    val vpnState by vpnRepository.state.collectAsState()



    val subscriptionViewModel: SubscriptionViewModel =
        viewModel(

            factory =
                SubscriptionViewModelFactory(

                    subscriptionRepository,

                    subscriptionImportEngine

                )

        )



    val subscriptionState by
        subscriptionViewModel.state.collectAsState()



    var currentTab by remember {
        mutableStateOf(
            FovixTab.HOME
        )
    }



    var mode by remember {
        mutableStateOf(
            UserMode.SIMPLE
        )
    }



    var scenario by remember {
        mutableStateOf(
            ProtectionScenario.EVERYDAY
        )
    }



    var showSubscriptionSheet by remember {
        mutableStateOf(false)
    }




    Scaffold(

        bottomBar = {

            FovixBottomBar(

                selected = currentTab,

                connectionStatus = vpnState.status,

                onTabSelected = {

                    currentTab = it

                }

            )

        }


    ) { padding ->



        Box(

            modifier =
                Modifier

                    .fillMaxSize()

                    .padding(padding)

        ) {



            when(currentTab) {



                FovixTab.HOME -> {



                    HomeDashboard(


                        mode = mode,


                        status =
                            vpnState.status,


                        server =
                            vpnState.server,


                        download =
                            vpnState.download,


                        upload =
                            vpnState.upload,


                        scenario =
                            scenario,


                        vpnSubscription =

                            subscriptionState.selected

                                ?: subscriptionState.subscriptions.firstOrNull(),



                        onConnectClick = {



                        },



                        onOpenSubscriptions = {

                            showSubscriptionSheet = true

                        },



                        onScenarioClick = {

                            scenario = it

                        },



                        onModeClick = {


                            mode = when(mode) {


                                UserMode.SIMPLE ->
                                    UserMode.ADVANCED


                                UserMode.ADVANCED ->
                                    UserMode.EXPERT


                                UserMode.EXPERT ->
                                    UserMode.SIMPLE

                            }

                        }


                    )


                }




                else -> {


                    Box(
                        modifier =
                            Modifier.fillMaxSize()
                    )


                }


            }





            if(showSubscriptionSheet) {



                SubscriptionActionSheet(



                    onDismiss = {

                        showSubscriptionSheet = false

                    },



                    url = subscriptionState.url,



                    onUrlChange = {

                        subscriptionViewModel.updateUrl(it)

                    },



                    loading =
                        subscriptionState.isLoading,



                    error =
                        subscriptionState.error,



                    onImport = {


                        subscriptionViewModel.importSubscription()


                    },



                    onPaste = {


                        // временно пусто
                        // сюда подключим ClipboardManager следующим шагом


                    },



                    onQr = {


                        // позже QR scanner


                    }



                )

            }



        }


    }


}