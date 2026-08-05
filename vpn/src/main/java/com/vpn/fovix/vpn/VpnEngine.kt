package com.vpn.fovix.vpn


import android.content.Context
import android.content.Intent
import android.util.Log


import com.vpn.fovix.domain.vpnprofile.VpnProfile
import com.vpn.fovix.domain.vpnstate.ConnectionStatus
import com.vpn.fovix.domain.vpnstate.VPNState
import com.vpn.fovix.domain.vpnstate.VpnController


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow



class VpnEngine(

    private val context: Context

) : VpnController {



    companion object {


        private const val TAG =
            "FOVIX_ENGINE"



        private var stateCallback:
                ((VPNState) -> Unit)? = null





        fun registerStateListener(

            callback: (VPNState) -> Unit

        ) {


            stateCallback = callback


        }





        fun notifyConnected(

            serverName: String

        ) {



            stateCallback?.invoke(

                VPNState(

                    status =
                        ConnectionStatus.CONNECTED,

                    server =
                        serverName

                )

            )



            Log.i(
                TAG,
                "CONNECTED $serverName"
            )


        }





        fun notifyError(

            message: String

        ) {


            stateCallback?.invoke(

                VPNState(

                    status =
                        ConnectionStatus.ERROR,

                    server =
                        message

                )

            )



            Log.e(
                TAG,
                "ERROR $message"
            )


        }



    }









    private val _state =

        MutableStateFlow(

            VPNState(

                status =
                    ConnectionStatus.DISCONNECTED,

                server =
                    "No server"

            )

        )





    override val state:

            StateFlow<VPNState>

        get() = _state







    init {


        registerStateListener {


                newState ->


            _state.value =
                newState


        }


    }









    override fun start(

        profile: VpnProfile

    ) {



        Log.i(

            TAG,

            "START ${profile.name} ${profile.server}:${profile.port}"

        )





        _state.value =

            VPNState(

                status =
                    ConnectionStatus.CONNECTING,

                server =
                    profile.name

            )







        try {



            val intent =

                Intent(

                    context,

                    FovixVpnService::class.java

                )







            /*
             * В Intent передаем только данные.
             * Domain объекты через Intent не передаем.
             */



            intent.putExtra(

                "SERVER_NAME",

                profile.name

            )



            intent.putExtra(

                "SERVER_HOST",

                profile.server

            )



            intent.putExtra(

                "SERVER_PORT",

                profile.port

            )



            intent.putExtra(

                "SERVER_UUID",

                profile.uuid

            )



            intent.putExtra(

                "SERVER_SNI",

                profile.sni

            )



            intent.putExtra(

                "SERVER_FP",

                profile.fingerprint

            )







            context.startService(

                intent

            )







            Log.i(

                TAG,

                "SERVICE STARTED"

            )



        }

        catch(e: Exception) {



            Log.e(

                TAG,

                "START FAILED",

                e

            )




            notifyError(

                e.message
                    ?: "START ERROR"

            )


        }


    }









    override fun stop() {



        Log.i(

            TAG,

            "STOP"

        )





        try {



            SingBoxNative.stop()





            context.stopService(

                Intent(

                    context,

                    FovixVpnService::class.java

                )

            )



        }

        catch(e: Exception) {



            Log.e(

                TAG,

                "STOP FAILED",

                e

            )


        }







        _state.value =

            VPNState(

                status =
                    ConnectionStatus.DISCONNECTED,

                server =
                    "No server"

            )


    }









    fun isRunning():

            Boolean {


        return try {


            SingBoxNative.isRunning()


        }

        catch(e: Exception) {


            false


        }


    }




}