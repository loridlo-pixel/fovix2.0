package com.vpn.fovix.config

import android.util.Log
import com.vpn.fovix.vpn.VPNServer
import org.json.JSONArray
import org.json.JSONObject


object SingBoxConfigBuilder {


    fun build(
        server: VPNServer
    ): String {


        val config = JSONObject()


        /*
         * TUN
         *
         * Android VPNService already creates TUN.
         * sing-box only receives FD and processes packets.
         */

        val inbounds = JSONArray()


        val tun = JSONObject().apply {

            put(
                "type",
                "tun"
            )

            put(
                "tag",
                "tun-in"
            )

            put(
                "mtu",
                1500
            )


            put(
                "address",
                JSONArray().apply {

                    put(
                        "172.19.0.1/30"
                    )

                }
            )


            put(
                "auto_route",
                false
            )


            put(
                "strict_route",
                false
            )


            put(
                "stack",
                "gvisor"
            )

        }


        inbounds.put(tun)


        config.put(
            "inbounds",
            inbounds
        )



        /*
         * OUTBOUND VLESS
         */

        val outbounds = JSONArray()


        val proxy = JSONObject().apply {


            put(
                "type",
                server.protocol
            )


            put(
                "tag",
                "proxy"
            )


            put(
                "server",
                server.address
            )


            put(
                "server_port",
                server.port
            )


            put(
                "uuid",
                server.uuid
            )


            /*
             * Basic TLS VLESS.
             *
             * uTLS отключен для первого
             * рабочего теста.
             * После подтверждения соединения
             * вернем fingerprint.
             */

            put(
                "tls",
                JSONObject().apply {


                    put(
                        "enabled",
                        true
                    )


                    put(
                        "server_name",
                        server.sni
                    )


                }
            )

        }


        outbounds.put(proxy)



        /*
         * DIRECT fallback
         */

        outbounds.put(
            JSONObject().apply {


                put(
                    "type",
                    "direct"
                )


                put(
                    "tag",
                    "direct"
                )

            }
        )



        config.put(
            "outbounds",
            outbounds
        )



        /*
         * ROUTING
         */

        config.put(
            "route",
            JSONObject().apply {


                put(
                    "final",
                    "proxy"
                )


            }
        )



        /*
         * DNS
         */

        config.put(
            "dns",
            JSONObject().apply {


                put(
                    "servers",
                    JSONArray().apply {


                        put(
                            JSONObject().apply {

                                put(
                                    "tag",
                                    "dns-cloudflare"
                                )

                                put(
                                    "address",
                                    "1.1.1.1"
                                )

                            }
                        )

                    }
                )


                put(
                    "final",
                    "dns-cloudflare"
                )


            }
        )



        val result =
            config.toString()



        Log.d(
            "FOVIX",
            "SINGBOX CONFIG=$result"
        )


        return result

    }

}