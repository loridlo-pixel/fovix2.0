package com.vpn.fovix.vpn


object SingBoxConfigProvider {


    fun build(): String {

        return """
        {
          "log": {
            "level": "debug"
          },

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
              "type": "direct",
              "tag": "direct"
            }
          ],

          "route": {
            "auto_detect_interface": true,
            "final": "proxy"
          }
        }
        """.trimIndent()

    }

}