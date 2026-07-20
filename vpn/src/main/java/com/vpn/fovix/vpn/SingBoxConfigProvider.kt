package com.vpn.fovix.vpn


object SingBoxConfigProvider {


    fun getConfig(): String {


        return """
        {
          "log": {
            "level": "info"
          },


          "dns": {
            "servers": [
              {
                "tag": "dns-direct",
                "address": "1.1.1.1"
              }
            ],
            "final": "dns-direct"
          },


          "inbounds": [
            {
              "type": "tun",
              "tag": "tun-in",

              "interface_name": "fovix0",

              "inet4_address": "10.0.0.1/30",

              "auto_route": false,

              "strict_route": false,

              "stack": "system"
            }
          ],


          "outbounds": [

            {
              "type": "vless",
              "tag": "proxy",

              "server": "ai.noooo.win",

              "server_port": 443,

              "uuid": "c5c1c20f-691d-4850-988c-ee463f4799ad",


              "tls": {

                "enabled": true,

                "server_name": "cdn-v1-6a51ff3b.noooo.win",

                "alpn": [
                  "h2"
                ],


                "utls": {

                  "enabled": true,

                  "fingerprint": "firefox"

                }

              },


              "transport": {

                "type": "xhttp",

                "path": "/",

                "mode": "stream-one"

              }

            },


            {
              "type": "direct",

              "tag": "direct"
            }

          ],


          "route": {

            "auto_detect_interface": false,

            "final": "proxy"

          }

        }
        """.trimIndent()

    }


}