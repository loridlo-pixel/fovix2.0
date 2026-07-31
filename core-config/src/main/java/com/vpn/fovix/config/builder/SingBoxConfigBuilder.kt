package com.vpn.fovix.config.builder

import com.vpn.fovix.config.model.FovixConfig
import org.json.JSONArray
import org.json.JSONObject
import android.util.Log


object SingBoxConfigBuilder {


    private const val TAG = "FOVIX-CONFIG"


    fun build(
        config: FovixConfig
    ): String {


        Log.d(TAG, "BUILD START")

        Log.d(TAG, "server=${config.server}")
        Log.d(TAG, "port=${config.port}")
        Log.d(TAG, "protocol=${config.protocol}")
        Log.d(TAG, "security=${config.security}")


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

        Log.d(TAG, "LOG OK")



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
                                    "dns-remote"
                                )
                                .put(
                                    "address",
                                    "https://1.1.1.1/dns-query"
                                )
                                .put(
                                    "detour",
                                    "proxy"
                                )
                        )
                )
                .put(
                    "final",
                    "dns-remote"
                )
                .put(
                    "strategy",
                    "prefer_ipv4"
                )
        )


        Log.d(TAG,"DNS OK")



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


        Log.d(TAG,"TUN OK")



        /*
            VLESS
         */

        val vless =
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


        config.flow?.let {

            vless.put(
                "flow",
                it
            )

        }



        /*
            TLS
         */


        val tls =
            JSONObject()
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


        Log.d(TAG,"VLESS OK")



        /*
            OUTBOUNDS
         */


        root.put(
            "outbounds",
            JSONArray()
                .put(
                    vless
                )
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


        Log.d(TAG,"OUTBOUNDS OK")



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
                    "rules",
                    JSONArray()
                        .put(
                            JSONObject()
                                .put(
                                    "protocol",
                                    "dns"
                                )
                                .put(
                                    "action",
                                    "hijack-dns"
                                )
                        )
                )
                .put(
                    "final",
                    "proxy"
                )
        )


        Log.d(TAG,"ROUTE OK")



        val result = root.toString()


        Log.d(
            TAG,
            "CONFIG SIZE=${result.length}"
        )


        Log.d(
            TAG,
            result
        )


        Log.d(TAG,"BUILD FINISH")


        return result
    }
}