package com.vpn.fovix.domain.vpnprofile


import android.os.Parcelable
import kotlinx.parcelize.Parcelize



@Parcelize
data class VpnProfile(

    val name: String,

    val country: String,

    val server: String,

    val port: Int,

    val uuid: String,

    val sni: String,

    val fingerprint: String = "chrome"

) : Parcelable