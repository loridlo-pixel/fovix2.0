package com.vpn.fovix.app.presentation


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier

import com.vpn.fovix.app.presentation.navigation.FovixBottomBar
import com.vpn.fovix.app.presentation.navigation.FovixTab

import com.vpn.fovix.app.presentation.theme.FovixTheme

import com.vpn.fovix.app.presentation.home.HomeScreen
import com.vpn.fovix.app.presentation.servers.ServersScreen
import com.vpn.fovix.app.presentation.settings.SettingsScreen
import com.vpn.fovix.app.presentation.subscription.SubscriptionScreen
import com.vpn.fovix.app.presentation.subscription.SubscriptionUiState
import com.vpn.fovix.app.presentation.home.UserMode



@Composable
fun FovixApp() {


    var currentTab by remember {

        mutableStateOf(
            FovixTab.HOME
        )

    }


    var userMode by remember {

        mutableStateOf(
            UserMode.SIMPLE
        )

    }



    FovixTheme {


        Column(

            modifier = Modifier
                .fillMaxSize()

        ) {



            Box(

                modifier = Modifier
                    .fillMaxSize()

            ) {



                when(currentTab) {



                    FovixTab.HOME -> {


                        HomeScreen()


                    }




                    FovixTab.SERVERS -> {


                        ServersScreen(

                            selectedServer = "",

                            onServerSelected = {

                            },

                            onBack = {

                                currentTab = FovixTab.HOME

                            }

                        )


                    }




                    FovixTab.DOCTOR -> {


                        // пока заглушка Beta 1.0

                        HomeScreen()


                    }




                    FovixTab.SUBSCRIPTION -> {


                        SubscriptionScreen(

                            state = SubscriptionUiState(),

                            onInputChange = {

                            },

                            onImport = {

                            },

                            onBack = {

                                currentTab = FovixTab.HOME

                            }

                        )


                    }




                    FovixTab.SETTINGS -> {


                        SettingsScreen(

                            mode = userMode,

                            onModeChange = {

                                userMode = it

                            },

                            onBack = {

                                currentTab = FovixTab.HOME

                            }

                        )


                    }



                }


            }





            FovixBottomBar(

                selected = currentTab,

                onSelected = {

                    currentTab = it

                },

                onCoreClick = {

                    currentTab = FovixTab.HOME

                }

            )


        }


    }


}