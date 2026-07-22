package com.vpn.fovix.data.importer


import com.vpn.fovix.data.subscription.Base64SubscriptionDecoder
import com.vpn.fovix.data.subscription.SubscriptionDecoder
import com.vpn.fovix.domain.server.ServerProfile



class SubscriptionImportEngine {



    private val decoders: List<SubscriptionDecoder> =
        listOf(

            Base64SubscriptionDecoder()

        )





    fun import(
        source: String
    ): List<ServerProfile> {



        for (
            decoder in decoders
        ) {


            if (
                decoder.canDecode(source)
            ) {


                return decoder.decode(
                    source
                )


            }

        }



        throw IllegalArgumentException(
            "Unsupported subscription format"
        )

    }


}