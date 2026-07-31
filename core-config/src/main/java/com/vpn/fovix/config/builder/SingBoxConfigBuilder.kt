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
                    "debug"
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
                        .put(
                            "auto_route",
                            true
                        )
                        .put(
                            "strict_route",
                            true
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
                "packet_encoding",
                config.packetEncoding
            )


        config.flow?.let {
            vless.put(
                "flow",
                it
            )
        }


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
                .put("h2")
                .put("http/1.1")
        )


        vless.put(
            "tls",
            tls
        )


        /*
            OUTBOUNDS
         */

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