package com.vpn.fovix.vpn

import android.content.Intent
import android.net.VpnService
import android.os.IBinder


class VpnServiceImpl : VpnService() {


    override fun onBind(intent: Intent?): IBinder? {
        return super.onBind(intent)
    }


    override fun onCreate() {
        super.onCreate()

        // STEP 004
        // VPN service layer created
        // Sing-box integration comes later
    }


    override fun onDestroy() {
        super.onDestroy()

        // stop VPN engine here later
    }
}
