package com.vpn.fovix.app.presentation


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


import com.vpn.fovix.app.presentation.home.HomeDashboard
import com.vpn.fovix.app.presentation.home.UserMode

import com.vpn.fovix.app.presentation.navigation.FovixBottomBar
import com.vpn.fovix.app.presentation.navigation.FovixTab

import com.vpn.fovix.app.presentation.servers.ServersScreen
import com.vpn.fovix.app.presentation.settings.SettingsScreen

import com.vpn.fovix.data.repository.VpnRepository



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

            modifier = Modifier

                .fillMaxSize()

                .padding(padding)

        ) {



            when(currentTab) {



                FovixTab.HOME -> {



                    HomeDashboard(


                        mode = mode,


                        status = vpnState.status,


                        server = vpnState.server,


                        download = vpnState.download,


                        upload = vpnState.upload,


                        onConnectClick = {


                            vpnRepository.toggle()


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


                        onServerSelected = {


                            vpnRepository.startVpn(it)


                        },


                        onBack = {


                            currentTab = FovixTab.HOME


                        }


                    )


                }






                FovixTab.DOCTOR -> {



                    Text(


                        text = "Network Doctor",


                        modifier = Modifier

                            .padding(30.dp)


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



                    Text(


                        text = "Profile",


                        modifier = Modifier

                            .padding(30.dp)


                    )


                }



            }



        }



    }



}