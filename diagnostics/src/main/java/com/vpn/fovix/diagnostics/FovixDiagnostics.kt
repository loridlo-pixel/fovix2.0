package com.vpn.fovix.diagnostics


object FovixDiagnostics {


    // Новый API через события

    fun event(
        event: FovixEvent,
        details: String = ""
    ) {

        val message =
            if(details.isBlank()) {

                event.code

            } else {

                "${event.code} | $details"

            }


        FovixLogger.info(
            message
        )

    }



    // Старый API для совместимости

    fun event(
        message: String
    ) {

        FovixLogger.info(
            message
        )

    }



    fun info(
        message: String
    ) {

        FovixLogger.info(
            message
        )

    }



    fun info(
        message: String,
        details: Any?
    ) {

        if(details != null) {

            FovixLogger.info(
                "$message | $details"
            )

        } else {

            FovixLogger.info(
                message
            )

        }

    }




    fun error(
        message: String,
        throwable: Throwable? = null
    ) {

        FovixLogger.error(
            message,
            throwable
        )

    }


}