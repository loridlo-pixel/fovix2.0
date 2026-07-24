package com.vpn.fovix.diagnostics

import android.util.Log
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap


object FovixDiagnostics {


    private const val TAG = "FOVIX_DIAG"


    private val sessionId =
        UUID.randomUUID()
            .toString()
            .substring(0,8)



    private val events =
        ConcurrentHashMap<String,String>()



    fun startSession() {

        events.clear()

        Log.e(
            TAG,
            """
            ==============================
            FOVIX DIAGNOSTIC SESSION START
            SESSION=$sessionId
            ==============================
            """.trimIndent()
        )

    }



    fun event(
        name:String,
        value:String = ""
    ) {


        events[name] = value


        Log.e(
            TAG,
            "$name ${if(value.isNotEmpty()) "= $value" else ""}"
        )

    }




    fun error(
        stage:String,
        throwable:Throwable
    ) {


        events["ERROR_$stage"] =
            throwable.message ?: "unknown"



        Log.e(
            TAG,
            "ERROR $stage",
            throwable
        )

    }




    fun snapshot():Map<String,String> {

        return events.toMap()

    }



    fun session():String {

        return sessionId

    }

}