package com.vpn.fovix.data.repository


import com.vpn.fovix.domain.vpnprofile.VpnProfile
import com.vpn.fovix.domain.vpnstate.ConnectionStatus
import com.vpn.fovix.domain.vpnstate.VPNState
import com.vpn.fovix.domain.vpnstate.VpnController

import kotlinx.coroutines.flow.StateFlow



class VpnRepository(

    private val vpnController: VpnController

) {



    val state: StateFlow<VPNState>

        get() = vpnController.state





    fun startVpn(

        profile: VpnProfile

    ) {


        vpnController.start(

            profile

        )


    }





    fun disconnect(){


        vpnController.stop()


    }





    fun toggle(

        profile: VpnProfile

    ){


        when(

            state.value.status

        ){


            ConnectionStatus.CONNECTED -> {


                disconnect()


            }



            else -> {


                startVpn(

                    profile

                )


            }


        }


    }



}