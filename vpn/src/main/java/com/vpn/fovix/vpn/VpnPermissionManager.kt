package com.vpn.fovix.vpn

import android.content.Context
import android.content.Intent
import android.net.VpnService


object VpnPermissionManager {


    fun getPermissionIntent(context: Context): Intent? {

        return VpnService.prepare(context)

    }

}
