package com.vpn.fovix.data.subscription


import com.vpn.fovix.domain.server.ServerProfile


interface SubscriptionDecoder {


    fun canDecode(
        input: String
    ): Boolean



    fun decode(
        input: String
    ): List<ServerProfile>


}