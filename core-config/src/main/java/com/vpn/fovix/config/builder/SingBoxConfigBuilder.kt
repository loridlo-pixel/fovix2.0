package com.vpn.fovix.config.builder

import com.vpn.fovix.config.model.FovixConfig
import com.vpn.fovix.config.model.FovixProxy
import org.json.JSONArray
import org.json.JSONObject


object SingBoxConfigBuilder {


    fun build(
        config: FovixConfig
    ): String {


        val root = JSONObject()


        val outbounds = JSONArray()


        config.proxies.forEach { proxy ->

            outbounds.put(
                buildProxy(proxy)
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
            "log",
            JSONObject()
                .put(
                    "level",
                    "info"
                )
        )


        root.put(
            "outbounds",
            outbounds
        )


        root.put(
            "route",
            JSONObject()
                .put(
                    "final",
                    config.finalOutbound
                )
        )


        return root.toString(2)

    }





    private fun buildProxy(
        proxy: FovixProxy
    ): JSONObject {


        val json =
            JSONObject()


        json.put(
            "type",
            proxy.type
        )


        json.put(
            "tag",
            proxy.tag
        )


        json.put(
            "server",
            proxy.server
        )


        json.put(
            "server_port",
            proxy.serverPort
        )



        proxy.uuid?.let {

            json.put(
                "uuid",
                it
            )

        }



        proxy.password?.let {

            json.put(
                "password",
                it
            )

        }



        proxy.tls?.let {

            tls ->


            json.put(
                "tls",
                JSONObject()
                    .put(
                        "enabled",
                        tls.enabled
                    )
                    .put(
                        "server_name",
                        tls.serverName
                    )
            )

        }



        return json

    }


}