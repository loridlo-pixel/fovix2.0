package com.vpn.fovix.data.importer

import com.vpn.fovix.data.subscription.Base64SubscriptionDecoder
import com.vpn.fovix.data.subscription.SubscriptionDecoder
import com.vpn.fovix.data.subscription.SubscriptionDownloader
import com.vpn.fovix.domain.server.ServerProfile

class SubscriptionImportEngine(

    private val downloader: SubscriptionDownloader =
        SubscriptionDownloader()

) {

    private val decoders: List<SubscriptionDecoder> = listOf(

        Base64SubscriptionDecoder()

    )

    suspend fun importFromUrl(

        url: String

    ): List<ServerProfile> {

        val content = downloader.download(url)

        return import(content)

    }

    fun import(

        source: String

    ): List<ServerProfile> {

        for (decoder in decoders) {

            if (decoder.canDecode(source)) {

                return decoder.decode(source)

            }

        }

        throw IllegalArgumentException(
            "Unsupported subscription format"
        )

    }

}