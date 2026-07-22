package com.vpn.fovix.app

import android.content.Context
import com.vpn.fovix.data.repository.ServerRepository
import com.vpn.fovix.data.repository.VpnRepository
import com.vpn.fovix.vpn.VpnEngine

class AppContainer(

    context: Context

) {

    val vpnEngine =

        VpnEngine(

            context.applicationContext

        )



    val serverRepository =

        ServerRepository()



    val vpnRepository =

        VpnRepository(

            vpnController = vpnEngine

        )

}