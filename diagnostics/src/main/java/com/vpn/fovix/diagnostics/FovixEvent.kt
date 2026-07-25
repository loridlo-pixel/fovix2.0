package com.vpn.fovix.diagnostics


enum class FovixEvent(
    val code: String
) {


    VPN_SERVICE_CREATED(
        "vpn_service_created"
    ),


    VPN_START_COMMAND(
        "vpn_start_command"
    ),


    TUN_CREATE_STARTED(
        "tun_create_started"
    ),


    TUN_CREATED(
        "tun_created"
    ),


    CONFIG_GENERATED(
        "config_generated"
    ),


    ENGINE_START_REQUEST(
        "engine_start_request"
    ),


    ENGINE_STARTED(
        "engine_started"
    ),


    ENGINE_FAILED(
        "engine_failed"
    ),


    ENGINE_STOPPED(
        "engine_stopped"
    ),


    VPN_DESTROYED(
        "vpn_destroyed"
    ),


    ERROR(
        "error"
    )

}