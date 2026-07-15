package com.vpn.fovix.vpn


import android.content.Context
import android.content.Intent
import android.util.Log


class VpnEngine(

    private val context: Context

) {


    fun startService() {


        Log.e(
            "FOVIX",
            "VPN SERVICE START"
        )


        val intent = Intent(

            context,

            FovixVpnService::class.java

        )


        context.startService(intent)


        Log.e(
            "FOVIX",
            "VPN SERVICE START REQUESTED"
        )


    }





    fun stopService() {


        Log.e(
            "FOVIX",
            "VPN SERVICE STOP"
        )


        val intent = Intent(

            context,

            FovixVpnService::class.java

        )


        context.stopService(intent)


        Log.e(
            "FOVIX",
            "VPN SERVICE STOP REQUESTED"
        )


    }


}