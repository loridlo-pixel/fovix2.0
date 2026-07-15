package com.vpn.fovix.vpn


import org.json.JSONArray
import org.json.JSONObject


object SingBoxConfigBuilder {


    fun build(server: VPNServer): String {


        val outbound = when (server.protocol.lowercase()) {


            "vless" -> buildVless(server)

            "trojan" -> buildTrojan(server)

            "vmess" -> buildVmess(server)

            "shadowsocks" -> buildShadowsocks(server)


            else ->
                throw IllegalArgumentException(
                    "Unsupported protocol: ${server.protocol}"
                )

        }



        val root = JSONObject()



        root.put(
            "log",
            JSONObject()
                .put("level", "info")
        )



        root.put(
            "dns",
            JSONObject()

                .put(
                    "servers",
                    JSONArray()
                        .put(
                            JSONObject()
                                .put("tag", "dns")
                                .put("address", "1.1.1.1")
                        )
                )

                .put(
                    "final",
                    "dns"
                )
        )



        root.put(
            "inbounds",
            JSONArray()
                .put(

                    JSONObject()

                        .put("type", "tun")

                        .put("tag", "tun-in")

                        .put(
                            "interface_name",
                            "fovix0"
                        )

                        .put(
                            "inet4_address",
                            "172.19.0.1/30"
                        )

                        .put(
                            "auto_route",
                            true
                        )

                        .put(
                            "strict_route",
                            true
                        )

                        .put(
                            "stack",
                            "system"
                        )

                )
        )



        root.put(

            "outbounds",

            JSONArray()

                .put(outbound)

                .put(

                    JSONObject()

                        .put("type", "direct")

                        .put("tag", "direct")

                )

        )



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







    private fun buildVless(
        server: VPNServer
    ): JSONObject {


        val obj = JSONObject()



        obj.put(
            "type",
            "vless"
        )


        obj.put(
            "tag",
            "proxy"
        )


        obj.put(
            "server",
            server.address
        )


        obj.put(
            "server_port",
            server.port
        )


        obj.put(
            "uuid",
            server.uuid
        )



        obj.put(
            "tls",
            buildTls(server)
        )



        applyOptions(
            obj,
            server
        )



        return obj

    }







    private fun buildTrojan(
        server: VPNServer
    ): JSONObject {


        val obj = JSONObject()


        obj.put("type", "trojan")
        obj.put("tag", "proxy")
        obj.put("server", server.address)
        obj.put("server_port", server.port)
        obj.put("password", server.password)



        obj.put(
            "tls",
            buildTls(server)
        )


        applyOptions(
            obj,
            server
        )


        return obj

    }







    private fun buildVmess(
        server: VPNServer
    ): JSONObject {


        val obj = JSONObject()


        obj.put("type", "vmess")
        obj.put("tag", "proxy")
        obj.put("server", server.address)
        obj.put("server_port", server.port)
        obj.put("uuid", server.uuid)



        applyOptions(
            obj,
            server
        )


        return obj

    }







    private fun buildShadowsocks(
        server: VPNServer
    ): JSONObject {


        return JSONObject()

            .put("type", "shadowsocks")

            .put("tag", "proxy")

            .put("server", server.address)

            .put("server_port", server.port)

            .put("method", server.security)

            .put("password", server.password)

    }







    private fun buildTls(
        server: VPNServer
    ): JSONObject {


        val tls = JSONObject()


        tls.put(
            "enabled",
            true
        )


        server.sni?.let {

            tls.put(
                "server_name",
                it
            )

        }



        tls.put(

            "utls",

            JSONObject()

                .put(
                    "enabled",
                    true
                )

                .put(
                    "fingerprint",
                    server.fingerprint ?: "chrome"
                )

        )



        return tls

    }







    private fun applyOptions(
    target: JSONObject,
    server: VPNServer
) {

    server.options?.forEach { (key, value) ->

        when (value) {

            is Map<*, *> -> {

                target.put(
                    key,
                    JSONObject(value)
                )

            }


            is JSONArray -> {

                target.put(
                    key,
                    value
                )

            }


            is List<*> -> {

                target.put(
                    key,
                    JSONArray(value)
                )

            }


            else -> {

                target.put(
                    key,
                    value
                )

            }

        }

    }

}

}
