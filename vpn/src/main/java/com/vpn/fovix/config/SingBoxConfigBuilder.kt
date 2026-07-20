package com.vpn.fovix.config

import com.vpn.fovix.vpn.VPNServer
import org.json.JSONArray
import org.json.JSONObject


object SingBoxConfigBuilder {


    fun build(
        server: VPNServer
    ): String {


        val config = JSONObject()



        /*
         * TUN INBOUND
         */

        val inbounds = JSONArray()

        val tun = JSONObject()


        tun.put(
            "type",
            "tun"
        )


        tun.put(
            "tag",
            "tun-in"
        )


        /*
         * ВАЖНО:
         * Android уже создает TUN через VpnService.
         * Sing-box получает FD.
         */

        tun.put(
            "interface_name",
            "fovix0"
        )


       tun.put(
    "address",
    JSONArray().apply {

        put(
            "10.0.0.1/30"
        )

    }
)


        tun.put(
            "mtu",
            1500
        )


        tun.put(
            "stack",
            "system"
        )


        tun.put(
            "auto_route",
            false
        )


        tun.put(
            "strict_route",
            false
        )


        inbounds.put(
            tun
        )


        config.put(
            "inbounds",
            inbounds
        )




        /*
         * OUTBOUND VLESS
         */

        val outbounds = JSONArray()


        val proxy = JSONObject()


        proxy.put(
            "type",
            server.protocol
        )


        proxy.put(
            "tag",
            "proxy"
        )


        proxy.put(
            "server",
            server.address
        )


        proxy.put(
            "server_port",
            server.port
        )


        proxy.put(
            "uuid",
            server.uuid
        )


        proxy.put(
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


                put(
                    "utls",
                    JSONObject().apply {


                        put(
                            "enabled",
                            true
                        )


                        put(
                            "fingerprint",
                            server.fingerprint
                        )


                    }
                )


            }
        )


        outbounds.put(
            proxy
        )



        val direct = JSONObject()


        direct.put(
            "type",
            "direct"
        )


        direct.put(
            "tag",
            "direct"
        )


        outbounds.put(
            direct
        )



        config.put(
            "outbounds",
            outbounds
        )




        /*
         * ROUTE
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



        return config.toString()

    }


}