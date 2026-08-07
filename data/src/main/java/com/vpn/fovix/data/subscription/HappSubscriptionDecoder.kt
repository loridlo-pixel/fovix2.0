package com.vpn.fovix.data.subscription


import com.vpn.fovix.domain.server.ServerProfile


class HappSubscriptionDecoder : SubscriptionDecoder {


    override fun canDecode(
        input: String
    ): Boolean {


        return input.startsWith(
            "happ://",
            ignoreCase = true
        )

    }




    override fun decode(
        input: String
    ): List<ServerProfile> {


        val payload =
            input.removePrefix(
                "happ://"
            )



        val parts =
            payload.split("/")



        if(parts.size < 2) {

            throw IllegalArgumentException(
                "Invalid Happ link"
            )

        }



        val method =
            parts[0]



        val data =
            parts[1]




        println(
            "HAPP METHOD = $method"
        )


        println(
            "HAPP DATA = $data"
        )



        /*
            Здесь позже будет:

            crypt4 decrypt

            Сейчас проверяем,
            что внутри.
        */



        throw IllegalArgumentException(

            "Happ crypt4 decoder not implemented yet"

        )


    }


}