package com.vpn.fovix.data.importer


import com.vpn.fovix.data.subscription.Base64SubscriptionDecoder
import com.vpn.fovix.data.subscription.SubscriptionDecoder
import com.vpn.fovix.data.subscription.SubscriptionDownloader

import com.vpn.fovix.domain.server.ServerProfile
import com.vpn.fovix.domain.subscription.VpnSubscription

import java.util.UUID



class SubscriptionImportEngine(


    private val downloader: SubscriptionDownloader =
        SubscriptionDownloader()


) {



    private val decoders:
            List<SubscriptionDecoder> = listOf(

        Base64SubscriptionDecoder()

    )





    suspend fun importSubscriptionFromUrl(

        url: String

    ): VpnSubscription {



        val content =
            downloader.download(url)



        val servers =
            import(content)



        return VpnSubscription(

            id = UUID.randomUUID().toString(),

            name = extractName(url),

            url = url,

            servers = servers,

            isActive = true

        )

    }








    fun import(

        source: String

    ): List<ServerProfile> {



        for(decoder in decoders) {



            if(decoder.canDecode(source)) {



                return decoder.decode(source)


            }


        }





        throw IllegalArgumentException(

            "Unsupported subscription format"

        )

    }








    private fun extractName(

        url: String

    ): String {



        return try {



            val host =
                url
                    .replace(
                        "https://",
                        ""
                    )
                    .replace(
                        "http://",
                        ""
                    )
                    .split("/")[0]



            host


        }
        catch(
            e: Exception
        ) {



            "VPN Provider"


        }


    }



}