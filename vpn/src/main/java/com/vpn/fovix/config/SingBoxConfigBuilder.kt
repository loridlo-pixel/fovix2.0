package com.vpn.fovix.config

import com.vpn.fovix.vpn.VPNServer


object SingBoxConfigBuilder {


    fun build(
        server: VPNServer
    ): String {


        return """
        {
          "log": {
            "disabled": false,
            "level": "trace",
            "timestamp": true
          },


          "inbounds": [
            {
              "type": "tun",
              "tag": "tun-in",
              "interface_name": "tun0",
              "stack": "gvisor",
              "mtu": 1500,
              "auto_route": false,
              "strict_route": false
            }
          ],


          "outbounds": [
            {
              "type": "vless",
              "tag": "proxy",

              "server": "${server.address}",
              "server_port": ${server.port},

              "uuid": "${server.uuid}",

              "tls": {
                "enabled": true,
                "server_name": "${server.sni}",

                "utls": {
                  "enabled": true,
                  "fingerprint": "${server.fingerprint}"
                }
              }
            },


            {
              "type": "direct",
              "tag": "direct"
            }
          ],


          "dns": {
            "servers": [
              {
                "tag": "dns-google",
                "address": "8.8.8.8",
                "detour": "proxy"
              }
            ],

            "final": "dns-google"
          },


          "route": {

            "auto_detect_interface": false,

            "rules": [
              {
                "ip_is_private": true,
                "outbound": "direct"
              }
            ],

            "final": "proxy"
          }


        }
        """.trimIndent()

    }

}