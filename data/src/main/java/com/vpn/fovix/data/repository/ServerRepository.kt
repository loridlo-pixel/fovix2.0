package com.vpn.fovix.data.repository


import com.vpn.fovix.domain.server.Protocol
import com.vpn.fovix.domain.server.ServerProfile
import com.vpn.fovix.domain.server.Transport


class ServerRepository {


    private val servers =
        mutableListOf(

            ServerProfile(

                id = "auto",

                name = "Auto",

                protocol = Protocol.UNKNOWN,

                address = "",

                port = 0,

                transport = Transport.UNKNOWN

            )

        )




    fun getAll(): List<ServerProfile> {

        return servers.toList()

    }





    fun getServerById(
        id: String
    ): ServerProfile? {

        return servers.find {

            it.id == id

        }

    }





    fun getFastest(): ServerProfile {

        return servers.first()

    }





    fun getBestByLoad(): ServerProfile {

        return servers.first()

    }





    fun addServer(
        server: ServerProfile
    ) {

        servers.removeAll {

            it.id == server.id

        }


        servers.add(server)

    }





    fun removeServer(
        id: String
    ) {

        servers.removeAll {

            it.id == id

        }

    }





}