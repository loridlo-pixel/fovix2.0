package com.vpn.fovix.vpn


data class VPNServer(

    val protocol: String,

    val address: String,

    val port: Int,


    /*
     * Универсальные идентификаторы
     */
    val uuid: String? = null,

    val password: String? = null,


    /*
     * TLS / Reality / сертификаты
     */
    val security: String? = null,

    val sni: String? = null,

    val fingerprint: String? = null,


    /*
     * Любые дополнительные параметры sing-box
     *
     * Пример:
     *
     * transport:
     * {
     *   "type":"grpc",
     *   "service_name":"vpn"
     * }
     *
     * или
     *
     * reality:
     * {
     *   "enabled":true,
     *   "public_key":"..."
     * }
     *
     */
    val options: Map<String, Any>? = null

)
