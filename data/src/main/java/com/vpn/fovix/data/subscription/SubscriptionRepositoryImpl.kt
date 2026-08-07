package com.vpn.fovix.data.subscription


import android.content.Context


import com.vpn.fovix.domain.server.Protocol
import com.vpn.fovix.domain.server.ServerProfile
import com.vpn.fovix.domain.server.TLSConfig
import com.vpn.fovix.domain.server.Transport
import com.vpn.fovix.domain.subscription.VpnSubscription


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


import org.json.JSONArray
import org.json.JSONObject




class SubscriptionRepositoryImpl(

    private val context: Context

) : SubscriptionRepository {



    companion object {

        private const val PREFS_NAME =
            "fovix_subscriptions"


        private const val KEY =
            "subscriptions"

    }






    private val prefs =
        context.getSharedPreferences(

            PREFS_NAME,

            Context.MODE_PRIVATE

        )






    private val _subscriptions =

        MutableStateFlow(

            load()

        )



    override val subscriptions: StateFlow<List<VpnSubscription>>

        get() = _subscriptions







    override fun getSubscriptions():

            List<VpnSubscription> {


        return _subscriptions.value


    }







    override fun getActive():

            VpnSubscription? {


        return _subscriptions.value.firstOrNull {

            it.isActive

        }


    }







    override fun addSubscription(

        subscription: VpnSubscription

    ) {



        val updated =

            _subscriptions.value

                .filter {

                    it.id != subscription.id

                }

                .toMutableList()



        updated.add(subscription)



        save(updated)



        _subscriptions.value =

            updated


    }







    override fun removeSubscription(

        id: String

    ) {



        val updated =

            _subscriptions.value

                .filter {

                    it.id != id

                }



        save(updated)



        _subscriptions.value =

            updated


    }









    private fun save(

        subscriptions: List<VpnSubscription>

    ) {



        val array = JSONArray()



        subscriptions.forEach { subscription ->



            val obj = JSONObject()



            obj.put(

                "id",

                subscription.id

            )


            obj.put(

                "name",

                subscription.name

            )


            obj.put(

                "url",

                subscription.url

            )


            obj.put(

                "active",

                subscription.isActive

            )





            val servers = JSONArray()



            subscription.servers.forEach { server ->



                val serverObj = JSONObject()



                serverObj.put(

                    "id",

                    server.id

                )



                serverObj.put(

                    "name",

                    server.name

                )



                serverObj.put(

                    "protocol",

                    server.protocol.name

                )



                serverObj.put(

                    "address",

                    server.address

                )



                serverObj.put(

                    "port",

                    server.port

                )



                serverObj.put(

                    "uuid",

                    server.uuid ?: ""

                )



                serverObj.put(

                    "transport",

                    server.transport.name

                )



                serverObj.put(

                    "subscriptionId",

                    server.subscriptionId ?: ""

                )



                val tls = JSONObject()



                tls.put(

                    "enabled",

                    server.tls.enabled

                )



                tls.put(

                    "serverName",

                    server.tls.serverName ?: ""

                )



                serverObj.put(

                    "tls",

                    tls

                )



                servers.put(

                    serverObj

                )


            }





            obj.put(

                "servers",

                servers

            )




            array.put(

                obj

            )



        }





        prefs.edit()

            .putString(

                KEY,

                array.toString()

            )

            .apply()


    }









    private fun load():

            List<VpnSubscription> {



        val result =

            mutableListOf<VpnSubscription>()



        val raw =

            prefs.getString(

                KEY,

                null

            )

            ?: return result





        try {



            val array =

                JSONArray(raw)





            for(i in 0 until array.length()) {



                val obj =

                    array.getJSONObject(i)





                val servers =

                    mutableListOf<ServerProfile>()





                val serversArray =

                    obj.optJSONArray(

                        "servers"

                    )







                if(serversArray != null) {



                    for(j in 0 until serversArray.length()) {



                        val serverObj =

                            serversArray.getJSONObject(j)





                        val tlsObj =

                            serverObj.optJSONObject(

                                "tls"

                            )







                        val tls =

                            TLSConfig(


                                enabled =

                                    tlsObj?.optBoolean(

                                        "enabled",

                                        false

                                    ) ?: false,



                                serverName =

                                    tlsObj?.optString(

                                        "serverName"

                                    )

                            )






                        servers.add(



                            ServerProfile(


                                id =

                                    serverObj.getString(

                                        "id"

                                    ),



                                name =

                                    serverObj.getString(

                                        "name"

                                    ),



                                protocol =

                                    Protocol.valueOf(

                                        serverObj.optString(

                                            "protocol",

                                            Protocol.VLESS.name

                                        )

                                    ),



                                address =

                                    serverObj.getString(

                                        "address"

                                    ),



                                port =

                                    serverObj.getInt(

                                        "port"

                                    ),



                                uuid =

                                    serverObj.optString(

                                        "uuid"

                                    )

                                        .ifBlank {

                                            null

                                        },



                                transport =

                                    Transport.valueOf(

                                        serverObj.optString(

                                            "transport",

                                            Transport.UNKNOWN.name

                                        )

                                    ),



                                tls = tls,



                                subscriptionId =

                                    serverObj.optString(

                                        "subscriptionId"

                                    )

                                        .ifBlank {

                                            null

                                        }



                            )



                        )



                    }



                }







                result.add(



                    VpnSubscription(



                        id =

                            obj.getString(

                                "id"

                            ),



                        name =

                            obj.getString(

                                "name"

                            ),



                        url =

                            obj.getString(

                                "url"

                            ),



                        servers =

                            servers,



                        isActive =

                            obj.optBoolean(

                                "active",

                                true

                            )



                    )



                )



            }





        }

        catch(e: Exception) {



            e.printStackTrace()



        }






        return result



    }



}