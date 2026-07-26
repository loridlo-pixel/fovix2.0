package com.vpn.fovix.config.builder

import com.vpn.fovix.config.model.FovixConfig
import org.json.JSONArray
import org.json.JSONObject


object SingBoxConfigBuilder {


    fun build(
        config: FovixConfig
    ): String {


        val root = JSONObject()



        root.put(
            "log",
            JSONObject()
                .put(
                    "level",
                    "info"
                )
        )



        /*
            Android creates TUN interface.
            Address is controlled by VpnService.
            Do NOT put address here.
        */

        val tun =
            JSONObject()


        tun.put(
            "type",
            "tun"
        )


        tun.put(
            "tag",
            "tun-in"
        )


        tun.put(
            "auto_route",
            true
        )


        tun.put(
            "strict_route",
            true
        )


        tun.put(
            "stack",
            "system"
        )



        root.put(
            "inbounds",
            JSONArray()
                .put(
                    tun
                )
        )



        val outbounds =
            JSONArray()



        config.outbounds.forEach { outbound ->


            val proxy =
                outbound.proxy
                    ?: return@forEach



            val item =
                JSONObject()



            item.put(
                "type",
                outbound.type
            )


            item.put(
                "tag",
                outbound.tag
            )


            item.put(
                "server",
                proxy.server
            )


            item.put(
                "server_port",
                proxy.serverPort
            )



            proxy.uuid?.let {

                item.put(
                    "uuid",
                    it
                )

            }



            proxy.password?.let {

                item.put(
                    "password",
                    it
                )

            }



            proxy.tls?.let { tls ->


                val tlsJson =
                    JSONObject()



                tlsJson.put(
                    "enabled",
                    tls.enabled
                )



                tls.serverName?.let {


                    tlsJson.put(
                        "server_name",
                        it
                    )


                }



                tls.fingerprint?.let {


                    tlsJson.put(
                        "utls",
                        JSONObject()
                            .put(
                                "enabled",
                                true
                            )
                            .put(
                                "fingerprint",
                                it
                            )
                    )


                }



                item.put(
                    "tls",
                    tlsJson
                )

            }



            outbounds.put(
                item
            )


        }




        outbounds.put(

            JSONObject()

                .put(
                    "type",
                    "direct"
                )

                .put(
                    "tag",
                    "direct"
                )

        )



        root.put(
            "outbounds",
            outbounds
        )



        return root.toString()

    }


}