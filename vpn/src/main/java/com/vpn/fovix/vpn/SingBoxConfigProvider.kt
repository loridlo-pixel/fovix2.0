package com.vpn.fovix.vpn

object SingBoxConfigProvider {

    fun build(): String {

        return """
        {
          "inbounds": [
            {
              "type": "tun",
              "tag": "tun-in",
              "interface_name": "fovix0",
              "address": [
                "172.19.0.1/30"
              ],
              "mtu": 1500,
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
                "utls": {
                  "enabled": true,
                  "fingerprint": "firefox"
                }
              }
            },
            {
              "type":"direct",
              "tag":"direct"
            }
          ],
          "route":{
            "final":"proxy"
          }
        }
        """.trimIndent()
    }
}