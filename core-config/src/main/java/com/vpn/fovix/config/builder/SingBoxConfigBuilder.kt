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
                                    "dns-cloudflare"
                                )

                                .put(
                                    "address",
                                    "https://1.1.1.1/dns-query"
                                )

                        )

                )

                .put(
                    "final",
                    "dns-cloudflare"
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

                )

        )





        /*
            VLESS
         */


        val vless = JSONObject()

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





        /*
            TLS
         */


        val tls = JSONObject()

            .put(
                "enabled",
                config.security == "tls"
            )

            .put(
                "server_name",
                config.sni
            )



        tls.put(

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



        tls.put(

            "alpn",

            JSONArray()

                .put(
                    "h2"
                )

                .put(
                    "http/1.1"
                )

        )



        vless.put(
            "tls",
            tls
        )






        root.put(

            "outbounds",

            JSONArray()

                .put(vless)

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

        )






        /*
            ROUTE
         */


        root.put(

            "route",

            JSONObject()

                .put(
                    "final",
                    "proxy"
                )

                .put(
                    "auto_detect_interface",
                    true
                )

        )



        return root.toString()

    }

}