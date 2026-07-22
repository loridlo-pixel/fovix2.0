package com.vpn.fovix.data.repository


import com.vpn.fovix.domain.server.ServerProfile



class ServerRepository {


    private val servers = mutableListOf(

        ServerProfile(

            id = "auto",

            name = "Auto",

            host = "",

            port = 0,

            country = "AUTO",

            latency = 0,

            load = 0,

            enabled = true

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


        return servers

            .filter {

                it.enabled

            }

            .minByOrNull {

                it.latency

            }
            ?: servers.first()



    }








    fun getBestByLoad(): ServerProfile {


        return servers

            .filter {

                it.enabled

            }

            .minByOrNull {

                it.load

            }
            ?: servers.first()



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








    fun updateLatency(

        id: String,

        latency: Int

    ) {


        val index = servers.indexOfFirst {

            it.id == id

        }


        if(index >= 0){


            val old = servers[index]


            servers[index] = old.copy(

                latency = latency

            )


        }


    }








    fun updateLoad(

        id: String,

        load: Int

    ) {


        val index = servers.indexOfFirst {

            it.id == id

        }


        if(index >= 0){


            val old = servers[index]


            servers[index] = old.copy(

                load = load

            )


        }


    }




}