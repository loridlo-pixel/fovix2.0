package com.vpn.fovix.diagnostics


object DiagnosticCollector {


    fun createReport(): DiagnosticReport {


        return DiagnosticReport(

            sessionId =
                FovixLogger.session(),


            events =
                FovixLogger.events()

        )

    }


}