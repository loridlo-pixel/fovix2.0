package com.vpn.fovix.data.subscription


import android.util.Base64
import com.vpn.fovix.data.import.XrayJsonImporter
import com.vpn.fovix.domain.server.ServerProfile



class Base64SubscriptionDecoder : SubscriptionDecoder {


    private val xrayImporter =
        XrayJsonImporter()



    override fun canDecode(
        input: String
    ): Boolean {


        return try {

            val decoded =
                decodeBase64(input)


            decoded.contains(
                "outbounds"
            )

        }
        catch (
            e: Exception
        ) {

            false

        }

    }





    override fun decode(
        input: String
    ): List<ServerProfile> {


        val decoded =
            decodeBase64(input)



        return xrayImporter.import(
            decoded
        )

    }





    private fun decodeBase64(
        input: String
    ): String {


        var value =
            input.trim()



        if (
            value.startsWith(
                "base64://"
            )
        ) {

            value =
                value.removePrefix(
                    "base64://"
                )

        }



        value =
            value
                .replace(
                    "\n",
                    ""
                )
                .replace(
                    "\r",
                    ""
                )



        return String(

            Base64.decode(
                value,
                Base64.DEFAULT
            )

        )

    }


}