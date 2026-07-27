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
        )




        /*
            ANDROID TUN

            FD comes from VpnService
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
        "stack",
        "gvisor"
    )
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
                                    "1.1.1.1"
                                )

                        )

                )

                .put(
                    "final",
                    "dns-cloudflare"
                )

        )









        /*
            OUTBOUNDS
        */


        val outbounds =
            JSONArray()



        outbounds.put(


            JSONObject()


                .put(
                    "type",
                    "vless"
                )


                .put(
                    "tag",
                    "proxy"
                )


                .put(
                    "server",
                    "yt.noooo.win"
                )


                .put(
                    "server_port",
                    8443
                )


                .put(
                    "uuid",
                    "c5c1c20f-691d-4850-988c-ee463f4799ad"
                )


                .put(
                    "flow",
                    "xtls-rprx-vision"
                )


                .put(

                    "tls",

                    JSONObject()

                        .put(
                            "enabled",
                            true
                        )

                        .put(
                            "server_name",
                            "ai-6a65eea0.noooo.win"
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
                                    "edge"
                                )

                        )


                )


        )







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









        /*
            ROUTE
        */


        root.put(

            "route",

            JSONObject()

                .put(
                    "auto_detect_interface",
                    false
                )

                .put(
                    "final",
                    "proxy"
                )

        )






        return root.toString()

    }


}