package com.vpn.fovix.data.import


import com.vpn.fovix.domain.server.Protocol
import com.vpn.fovix.domain.server.ServerProfile
import com.vpn.fovix.domain.server.TLSConfig
import com.vpn.fovix.domain.server.Transport
import org.json.JSONObject



class XrayJsonImporter : ConfigImporter {



    override fun canHandle(
        input: String
    ): Boolean {

        return try {

            val json =
                JSONObject(input)

            json.has("outbounds")

        } catch (
            e: Exception
        ) {

            false

        }

    }





    override fun import(
        input: String
    ): List<ServerProfile> {


        val result =
            mutableListOf<ServerProfile>()



        val root =
            JSONObject(input)



        val remarks =
            root.optString(
                "remarks",
                "Imported VLESS"
            )



        val outbounds =
            root.getJSONArray(
                "outbounds"
            )



        for (
            i in 0 until outbounds.length()
        ) {


            val outbound =
                outbounds.getJSONObject(i)



            if (
                outbound.optString("protocol")
                != "vless"
            ) {

                continue

            }



            val settings =
                outbound
                    .optJSONObject("settings")
                    ?: continue



            val vnext =
                settings
                    .optJSONArray("vnext")
                    ?.getJSONObject(0)
                    ?: continue



            val address =
                vnext
                    .optString("address")



            val port =
                vnext
                    .optInt(
                        "port",
                        443
                    )



            val users =
                vnext
                    .optJSONArray("users")
                    ?: continue



            val user =
                users
                    .getJSONObject(0)



            val uuid =
                user
                    .optString("id")



            val stream =
                outbound
                    .optJSONObject(
                        "streamSettings"
                    )



            val network =
                stream
                    ?.optString(
                        "network",
                        "tcp"
                    )
                    ?: "tcp"




            val transport =

                when(network) {


                    "xhttp" ->
                        Transport.XHTTP


                    "ws" ->
                        Transport.WS


                    "grpc" ->
                        Transport.GRPC


                    "httpupgrade" ->
                        Transport.HTTP_UPGRADE


                    "quic" ->
                        Transport.QUIC


                    "tcp" ->
                        Transport.TCP


                    else ->
                        Transport.UNKNOWN

                }





            val tlsSettings =
                stream
                    ?.optJSONObject(
                        "tlsSettings"
                    )



            val tls =

                TLSConfig(

                    enabled =
                        stream
                            ?.optString(
                                "security"
                            )
                            == "tls",


                    serverName =
                        tlsSettings
                            ?.optString(
                                "serverName",
                                ""
                            )
                            ?: "",


                    fingerprint =
                        tlsSettings
                            ?.optString(
                                "fingerprint",
                                ""
                            )
                            ?: ""

                )





            result.add(

                ServerProfile(

                    id =
                        uuid,


                    name =
                        remarks,


                    protocol =
                        Protocol.VLESS,


                    address =
                        address,


                    port =
                        port,


                    uuid =
                        uuid,


                    transport =
                        transport,


                    tls =
                        tls,


                    path =
                        stream
                            ?.optJSONObject(
                                "xhttpSettings"
                            )
                            ?.optString(
                                "path"
                            ),


                    host =
                        stream
                            ?.optJSONObject(
                                "xhttpSettings"
                            )
                            ?.optString(
                                "host"
                            ),


                    networkMode =
                        network

                )

            )



        }



        return result

    }



}