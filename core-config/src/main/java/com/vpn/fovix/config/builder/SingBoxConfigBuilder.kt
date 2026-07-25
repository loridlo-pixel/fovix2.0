package com.vpn.fovix.config.builder


import com.vpn.fovix.config.model.FovixConfig
import org.json.JSONArray
import org.json.JSONObject


object SingBoxConfigBuilder {


    fun build(
        config: FovixConfig
    ): String {


        val root = JSONObject()



        /*
         * INBOUNDS
         */

        val inbounds = JSONArray()

        val inbound = JSONObject()


        inbound.put(
            "type",
            config.inbound.type
        )


        inbound.put(
            "tag",
            config.inbound.tag
        )


        inbound.put(
            "mtu",
            config.inbound.mtu
        )


        inbound.put(
            "address",
            JSONArray().apply {
                put(config.inbound.address)
            }
        )


        inbound.put(
            "auto_route",
            config.inbound.autoRoute
        )


        inbound.put(
            "strict_route",
            config.inbound.strictRoute
        )


        inbound.put(
            "stack",
            config.inbound.stack
        )


        inbounds.put(inbound)


        root.put(
            "inbounds",
            inbounds
        )



        /*
         * OUTBOUNDS
         */

        val outbounds = JSONArray()



        config.outbounds.forEach { outbound ->


            val item = JSONObject()


            item.put(
                "type",
                outbound.type
            )


            item.put(
                "tag",
                outbound.tag
            )



            outbound.proxy?.let { proxy ->


                item.put(
                    "server",
                    proxy.server
                )


                item.put(
                    "server_port",
                    proxy.serverPort
                )



                proxy.uuid?.let { uuid ->

                    item.put(
                        "uuid",
                        uuid
                    )

                }



                proxy.password?.let { password ->

                    item.put(
                        "password",
                        password
                    )

                }



                proxy.tls?.let { tlsConfig ->


                    val tls = JSONObject()


                    tls.put(
                        "enabled",
                        tlsConfig.enabled
                    )



                    tlsConfig.serverName?.let { name ->

                        tls.put(
                            "server_name",
                            name
                        )

                    }



                    tlsConfig.fingerprint?.let { fingerprint ->


                        tls.put(
                            "utls",
                            JSONObject().apply {

                                put(
                                    "enabled",
                                    true
                                )


                                put(
                                    "fingerprint",
                                    fingerprint
                                )

                            }
                        )

                    }



                    item.put(
                        "tls",
                        tls
                    )

                }




                proxy.transport?.let { transportConfig ->


                    val transport =
                        JSONObject()


                    transportConfig.type?.let { type ->

                        transport.put(
                            "type",
                            type
                        )

                    }


                    transportConfig.path?.let { path ->

                        transport.put(
                            "path",
                            path
                        )

                    }


                    item.put(
                        "transport",
                        transport
                    )

                }


            }



            outbounds.put(item)


        }




        val direct = JSONObject()


        direct.put(
            "type",
            "direct"
        )


        direct.put(
            "tag",
            "direct"
        )


        outbounds.put(direct)



        root.put(
            "outbounds",
            outbounds
        )



        /*
         * ROUTE
         */

        val route = JSONObject()



        route.put(
            "auto_detect_interface",
            config.route.autoDetectInterface
        )


        route.put(
            "final",
            config.route.finalOutbound
        )



        root.put(
            "route",
            route
        )



        return root.toString(2)

    }

}