package com.vpn.fovix.data.repository


import com.vpn.fovix.domain.vpnstate.VPNState
import com.vpn.fovix.domain.vpnstate.VpnController
import kotlinx.coroutines.flow.StateFlow



class VpnRepository(

    private val vpnController: VpnController

) {



    val state: StateFlow<VPNState>

        get() = vpnController.state






    fun startVpn(

        server: Any? = null

    ) {


        vpnController.start(

            server

        )


    }








    fun disconnect(){


        vpnController.stop()


    }








    fun toggle(){


        val currentState = state.value



        when(

            currentState.status

        ){


            com.vpn.fovix.domain.vpnstate.ConnectionStatus.CONNECTED -> {


                disconnect()


            }



            else -> {


                startVpn(null)


            }


        }


    }



}