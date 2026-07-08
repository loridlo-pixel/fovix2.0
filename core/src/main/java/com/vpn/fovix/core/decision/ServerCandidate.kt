package com.vpn.fovix.core.decision


data class ServerCandidate(

    val name: String,

    val latency: Int,

    val load: Int,

    val country: String

)