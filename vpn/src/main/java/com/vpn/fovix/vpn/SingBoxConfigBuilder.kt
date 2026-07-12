package com.vpn.fovix.vpn


object SingBoxConfigBuilder {


    fun build(server: VPNServer): String {


        val outbound = when(server.protocol.lowercase()) {


            "vless" -> buildVless(server)


            else -> {
                throw Exception(
                    "Unsupported protocol: ${server.protocol}"
                )
            }

        }



        return """
        {
          "log": {
            "level": "info"
          },


          "dns": {

            "servers": [
              {
                "tag": "dns",
                "address": "1.1.1.1"
              }
            ],

            "final": "dns"

          },


          "inbounds": [

            {
              "type": "tun",
              "tag": "tun-in",

              "inet4_address": "172.19.0.1/30",

              "auto_route": true,

              "strict_route": true

            }

          ],



          "outbounds": [

            $outbound,


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





    private fun buildVless(
        server: VPNServer
    ): String {


        val transport = if(server.transport != null) {

            """
            ,

            "transport": {

                "type": "${server.transport}",

                "path": "${server.path ?: "/"}"

            }
            """

        } else {


            ""

        }





        return """

        {

          "type": "vless",

          "tag": "proxy",


          "server": "${server.address}",


          "server_port": ${server.port},


          "uuid": "${server.uuid}",



          "tls": {

            "enabled": true,


            "server_name": "${server.sni ?: server.address}",



            "utls": {

              "enabled": true,

              "fingerprint": "${server.fingerprint ?: "chrome"}"

            }

          }


          $transport

        }

        """.trimIndent()

    }


}