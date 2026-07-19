package com.vpn.fovix.config

import org.json.JSONArray
import org.json.JSONObject

object SingBoxConfigBuilder {

    fun build(): ByteArray {

        val config = JSONObject()

        /*
         * Android VPNService уже создаёт TUN.
         * Sing-box не должен создавать интерфейс.
         * FD передаётся через JNI.
         */

        val inbounds = JSONArray()

        val tun = JSONObject()
        tun.put("type", "tun")
        tun.put("tag", "tun-in")

        // ВАЖНО:
        // Android управляет маршрутизацией
        tun.put("auto_route", false)
        tun.put("strict_route", false)

        // Используем system stack
        tun.put("stack", "system")


        inbounds.put(tun)

        config.put(
            "inbounds",
            inbounds
        )


        /*
         * Временно добавляем минимальный outbound.
         * Нужен чтобы проверить запуск ядра.
         * VLESS добавим после успешного старта TUN.
         */

        val outbounds = JSONArray()

        val direct = JSONObject()
        direct.put("type", "direct")
        direct.put("tag", "direct")

        outbounds.put(direct)

        config.put(
            "outbounds",
            outbounds
        )


        return config.toString()
            .toByteArray(Charsets.UTF_8)
    }
}