package com.vpn.fovix.diagnostics


import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID
import java.util.concurrent.CopyOnWriteArrayList



object FovixLogger {


    private const val TAG = "FOVIX"


    private val sessionId =
        UUID.randomUUID()
            .toString()
            .take(8)
            .uppercase()



    private val events =
        CopyOnWriteArrayList<String>()





    fun session(): String {

        return sessionId

    }






    fun log(
        message: String
    ) {


        val time =
            SimpleDateFormat(
                "HH:mm:ss.SSS",
                Locale.US
            )
                .format(Date())



        val line =
            "$time | $message"



        events.add(line)



        Log.e(
            TAG,
            "[$sessionId] $message"
        )

    }







    fun info(
        message: String,
        extra: Any? = null
    ) {


        log(
            if(extra != null)
                "$message | $extra"
            else
                message
        )

    }







    fun success(
        message: String,
        extra: Any? = null
    ) {


        log(
            "SUCCESS: " +
            if(extra != null)
                "$message | $extra"
            else
                message
        )

    }







    fun error(
        message: String,
        extra: Any? = null
    ) {


        val result =
            when(extra){

                is Throwable ->
                    "$message | ${extra.message}"

                else ->
                    if(extra != null)
                        "$message | $extra"
                    else
                        message

            }



        log(
            "ERROR: $result"
        )

    }







    fun exception(
        message: String,
        throwable: Throwable? = null
    ) {


        error(
            message,
            throwable
        )

    }







    fun dump(): String {


        val builder =
            StringBuilder()



        builder.append(
            "FOVIX DIAGNOSTICS\n"
        )


        builder.append(
            "SESSION: $sessionId\n\n"
        )



        events.forEach {

            builder.append(it)
            builder.append("\n")

        }



        return builder.toString()

    }


}