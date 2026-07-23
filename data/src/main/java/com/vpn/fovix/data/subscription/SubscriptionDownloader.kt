package com.vpn.fovix.data.subscription

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class SubscriptionDownloader {

    suspend fun download(url: String): String =
        withContext(Dispatchers.IO) {

            val connection =
                URL(url).openConnection() as HttpURLConnection

            connection.requestMethod = "GET"
            connection.connectTimeout = 10000
            connection.readTimeout = 15000
            connection.setRequestProperty(
                "User-Agent",
                "FOVIX/1.0"
            )

            connection.inputStream.bufferedReader().use {

                it.readText()

            }

        }

}