package com.vpn.fovix.diagnostics


data class DiagnosticReport(

    val sessionId: String,

    val events: List<String>,

    val createdAt: Long = System.currentTimeMillis()

) {


    fun isHealthy(): Boolean {


        val started =
            events.any {

                it.contains(
                    "engine_started",
                    ignoreCase = true
                )

            }



        val failed =
            events.any {

                it.contains(
                    "ERROR",
                    ignoreCase = true
                )

            }



        return started && !failed

    }




    fun lastEvent(): String? {

        return events.lastOrNull()

    }




    fun status(): String {


        return if(isHealthy()) {

            "HEALTHY"

        } else {

            "FAILED"

        }

    }


}