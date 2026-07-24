package com.vpn.fovix.config


import com.vpn.fovix.vpn.VPNServer
import org.json.JSONArray
import org.json.JSONObject



object SingBoxConfigBuilder {



    fun build(
        server: VPNServer
    ): String {



        val vless =
            JSONObject()



        vless.put(
            "type",
            "vless"
        )


        vless.put(
            "tag",
            "proxy"
        )


        vless.put(
            "server",
            server.address
        )


        vless.put(
            "server_port",
            server.port
        )



        server.uuid?.let {

            vless.put(
                "uuid",
                it
            )

        }





        val direct =
            JSONObject()


        direct.put(
            "type",
            "direct"
        )


        direct.put(
            "tag",
            "direct"
        )





        val outbounds =
            JSONArray()



        outbounds.put(
            vless
        )


        outbounds.put(
            direct
        )






        val route =
            JSONObject()


        route.put(
            "final",
            "proxy"
        )






        val root =
            JSONObject()



        root.put(
            "log",
            JSONObject()
                .put(
                    "level",
                    "debug"
                )
        )



        root.put(
            "outbounds",
            outbounds
        )



        root.put(
            "route",
            route
        )




        return root.toString(2)

    }


}