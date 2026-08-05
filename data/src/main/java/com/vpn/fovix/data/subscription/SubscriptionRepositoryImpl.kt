package com.vpn.fovix.data.subscription


import android.content.Context

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



    override val subscriptions:
            StateFlow<List<VpnSubscription>>


    private val _subscriptions =
        MutableStateFlow(
            load()
        )



    init {

        subscriptions = _subscriptions

    }





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

        list: List<VpnSubscription>

    ) {


        val array =
            JSONArray()



        list.forEach {


            val obj =
                JSONObject()



            obj.put(
                "id",
                it.id
            )


            obj.put(
                "name",
                it.name
            )


            obj.put(
                "url",
                it.url
            )


            obj.put(
                "active",
                it.isActive
            )



            array.put(obj)

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


                        isActive =
                            obj.optBoolean(
                                "active",
                                true
                            )

                    )

                )


            }


        }
        catch(e: Exception){

            e.printStackTrace()

        }



        return result


    }


}