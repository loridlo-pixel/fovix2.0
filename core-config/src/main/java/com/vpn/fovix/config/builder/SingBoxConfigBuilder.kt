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
            LOG
         */

        root.put(
            "log",
            JSONObject()
                .put(
                    "level",
                    "info"
                )
                .put(
                    "timestamp",
                    true
                )
        )



        /*
            DNS
         */

        root.put(

            "dns",

            JSONObject()

                .put(

                    "servers",

                    JSONArray()

                        .put(

                            JSONObject()

                                .put(
                                    "tag",
                                    "dns-direct"
                                )

                                .put(
                                    "type",
                                    "udp"
                                )

                                .put(
                                    "server",
                                    "1.1.1.1"
                                )

                        )

                )

                .put(
                    "final",
                    "dns-direct"
                )

                .put(
                    "strategy",
                    "prefer_ipv4"
                )

        )





        /*
            TUN
         */


        root.put(

            "inbounds",

            JSONArray()

                .put(

                    JSONObject()

                        .put(
                            "type",
                            "tun"
                        )

                        .put(
                            "tag",
                            "tun-in"
                        )

                        .put(
                            "mtu",
                            1500
                        )

                        .put(
                            "stack",
                            "gvisor"
                        )

                        .put(
                            "auto_route",
                            true
                        )

                        .put(
                            "strict_route",
                            false
                        )

                        .put(
                            "sniff",
                            true
                        )

                        .put(
                            "sniff_override_destination",
                            true
                        )

                )

        )




        /*
            VLESS
         */


        val proxy = JSONObject()


            .put(
                "type",
                config.protocol
            )

            .put(
                "tag",
                "proxy"
            )

            .put(
                "server",
                config.server
            )

            .put(
                "server_port",
                config.port
            )

            .put(
                "uuid",
                config.uuid
            )

            .put(
                "flow",
                config.flow
            )

            .put(
                "packet_encoding",
                config.packetEncoding
            )





        val tls = JSONObject()


            .put(
                "enabled",
                true
            )

            .put(
                "server_name",
                config.sni
            )


            .put(

                "utls",

                JSONObject()

                    .put(
                        "enabled",
                        true
                    )

                    .put(
                        "fingerprint",
                        config.fingerprint
                    )

            )



        proxy.put(
            "tls",
            tls
        )





        /*
            OUTBOUNDS
         */


        root.put(

            "outbounds",

            JSONArray()

                .put(proxy)


                .put(

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


                .put(

                    JSONObject()

                        .put(
                            "type",
                            "block"
                        )

                        .put(
                            "tag",
                            "block"
                        )

                )

        )






        /*
            ROUTE
         */


        root.put(

            "route",

            JSONObject()

                .put(

                    "rules",

                    JSONArray()

                        .put(

                            JSONObject()

                                .put(
                                    "action",
                                    "hijack-dns"
                                )

                        )

                )

                .put(
                    "auto_detect_interface",
                    true
                )

                .put(
                    "final",
                    "proxy"
                )

        )




        return root.toString()

    }


}