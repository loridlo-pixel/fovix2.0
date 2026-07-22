package com.vpn.fovix.data.import


import com.vpn.fovix.domain.server.ServerProfile


interface ConfigImporter {


    fun canHandle(
        input: String
    ): Boolean



    fun import(
        input: String
    ): List<ServerProfile>


}