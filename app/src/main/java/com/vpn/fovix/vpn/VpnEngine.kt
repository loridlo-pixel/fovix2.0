package com.vpn.fovix.vpn


import android.content.Context
import android.content.Intent
import android.util.Log

import com.vpn.fovix.domain.vpnstate.ConnectionStatus
import com.vpn.fovix.domain.vpnstate.VPNState
import com.vpn.fovix.domain.vpnstate.VpnController

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow



class VpnEngine(

    private val context: Context

) : VpnController {



    companion object {

        private const val TAG = "FOVIX_ENGINE"

    }



    private val _state = MutableStateFlow(

        VPNState(

            status = ConnectionStatus.DISCONNECTED,

            server = "Auto"

        )

    )



    override val state: StateFlow<VPNState>

        get() = _state





    override fun start(

        server: Any?

    ) {



        Log.i(

            TAG,

            "START REQUEST"

        )



        val serverName =
            server?.toString()
                ?: "Auto"




        _state.value = VPNState(

            status = ConnectionStatus.CONNECTING,

            server = serverName

        )




        try {



            val intent = Intent(

                context,

                FovixVpnService::class.java

            )



            context.startService(intent)



            Log.i(

                TAG,

                "VPN SERVICE STARTED WAITING ENGINE"

            )



            /*
                ВАЖНО:

                Здесь больше НЕ ставим CONNECTED.

                Реальное подключение будет после:
                TUN CREATED
                ENGINE STARTED
                sing-box RUNNING

             */



        }
        catch(e: Exception) {



            Log.e(

                TAG,

                "VPN START FAILED",

                e

            )



            _state.value = VPNState(

                status = ConnectionStatus.ERROR,

                server = "Error"

            )


        }


    }







    override fun stop(){



        Log.i(

            TAG,

            "STOP REQUEST"

        )



        _state.value = VPNState(

            status = ConnectionStatus.DISCONNECTING

        )



        try {



            SingBoxNative.stop()



            val intent = Intent(

                context,

                FovixVpnService::class.java

            )



            context.stopService(intent)



        }
        catch(e: Exception){



            Log.e(

                TAG,

                "STOP ERROR",

                e

            )


        }





        _state.value = VPNState(

            status = ConnectionStatus.DISCONNECTED

        )


    }








    fun isRunning(): Boolean {


        return try {


            SingBoxNative.isRunning()


        }
        catch(e: Exception){


            false


        }


    }


}