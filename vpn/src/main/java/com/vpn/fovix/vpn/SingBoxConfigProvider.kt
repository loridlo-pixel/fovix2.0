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
                "tag": "google",
                "address": "https://dns.google/dns-query"
              }
            ]
          },

          "inbounds": [
            {
              "type": "tun",
              "tag": "tun-in",
              "interface_name": "fovix0",
              "inet4_address": "172.19.0.1/30",
              "auto_route": true,
              "strict_route": true
            }
          ],


          "outbounds": [
            {
              "type": "direct",
              "tag": "direct"
            }
          ]

        }
        """.trimIndent()

    }


}