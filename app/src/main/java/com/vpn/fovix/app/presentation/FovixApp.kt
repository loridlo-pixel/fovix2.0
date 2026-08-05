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


import com.vpn.fovix.app.presentation.home.HomeDashboard
import com.vpn.fovix.app.presentation.home.UserMode
import com.vpn.fovix.app.presentation.home.components.ProtectionScenario

import com.vpn.fovix.app.presentation.navigation.FovixBottomBar
import com.vpn.fovix.app.presentation.navigation.FovixTab

import com.vpn.fovix.app.presentation.servers.ServersScreen
import com.vpn.fovix.app.presentation.settings.SettingsScreen

import com.vpn.fovix.data.repository.VpnRepository

import com.vpn.fovix.domain.vpnprofile.VpnProfile




@Composable
fun FovixApp(

    vpnRepository: VpnRepository

) {



    val vpnState by vpnRepository.state.collectAsState()



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






    var selectedProfile by remember {


        mutableStateOf<VpnProfile?>(null)


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



    ) { paddingValues ->





        Box(


            modifier = Modifier

                .fillMaxSize()

                .padding(paddingValues)



        ) {



            when(currentTab) {



                FovixTab.HOME -> {



                    HomeDashboard(


                        mode = mode,


                        scenario = scenario,


                        status = vpnState.status,


                        server = vpnState.server,


                        download = vpnState.download,


                        upload = vpnState.upload,



                        onConnectClick = {



                            selectedProfile?.let {


                                vpnRepository.toggle(

                                    it

                                )


                            }



                        },




                        onScenarioClick = {


                            scenario = it


                        },





                        onProfileClick = {


                            currentTab = FovixTab.PROFILE


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









                FovixTab.SERVERS -> {



                    ServersScreen(



                        selectedServer = vpnState.server,



                        onServerSelected = { serverName ->





                            val profile = VpnProfile(



                                name = serverName,



                                country = "Unknown",



                                server = serverName,



                                port = 443,



                                uuid = "",



                                sni = serverName,



                                fingerprint = "chrome"



                            )





                            selectedProfile = profile





                            vpnRepository.startVpn(

                                profile

                            )





                        },





                        onBack = {



                            currentTab = FovixTab.HOME



                        }



                    )



                }









                FovixTab.SETTINGS -> {



                    SettingsScreen(



                        mode = mode,



                        onModeChange = {



                            mode = it



                        },



                        onBack = {



                            currentTab = FovixTab.HOME



                        }



                    )



                }









                FovixTab.PROFILE -> {



                    Box(



                        modifier = Modifier

                            .fillMaxSize()



                    ) {



                    }



                }









                FovixTab.DOCTOR -> {



                    Box(



                        modifier = Modifier

                            .fillMaxSize()



                    ) {



                    }



                }



            }



        }



    }



}