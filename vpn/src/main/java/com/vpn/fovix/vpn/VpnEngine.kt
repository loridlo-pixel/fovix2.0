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


        private const val TAG = "FOVIX_ENGINE"



        private var stateListener:
                ((VPNState) -> Unit)? = null




        fun registerStateListener(
            listener: (VPNState) -> Unit
        ) {

            stateListener = listener

        }




        fun notifyConnected(
            serverName: String
        ) {


            val state = VPNState(

                status = ConnectionStatus.CONNECTED,

                server = serverName

            )


            stateListener?.invoke(state)


            Log.i(
                TAG,
                "STATE CONNECTED $serverName"
            )

        }





        fun notifyError(
            message: String
        ) {


            val state = VPNState(

                status = ConnectionStatus.ERROR,

                server = message

            )


            stateListener?.invoke(state)


            Log.e(
                TAG,
                "STATE ERROR=$message"
            )

        }


    }






    private val _state = MutableStateFlow(

        VPNState(

            status = ConnectionStatus.DISCONNECTED,

            server = "None"

        )

    )





    override val state: StateFlow<VPNState>

        get() = _state







    init {


        registerStateListener {


                newState ->


            _state.value = newState


        }


    }









    override fun start(

        profile: VpnProfile

    ) {


        Log.i(
            TAG,
            "START VPN ${profile.name}"
        )




        _state.value = VPNState(

            status = ConnectionStatus.CONNECTING,

            server = profile.name

        )





        try {


            val intent = Intent(

                context,

                FovixVpnService::class.java

            )



            intent.putExtra(

                "VPN_PROFILE",

                profile

            )



            context.startService(intent)





            Log.i(
                TAG,
                "SERVICE STARTED WITH PROFILE ${profile.server}"
            )



        }
        catch(e: Exception) {


            Log.e(

                TAG,

                "START FAILED",

                e

            )



            _state.value = VPNState(

                status = ConnectionStatus.ERROR,

                server = profile.name

            )


        }


    }










    override fun stop() {



        Log.i(

            TAG,

            "STOP VPN"

        )




        _state.value = VPNState(

            status = ConnectionStatus.DISCONNECTING,

            server = "None"

        )





        try {


            SingBoxNative.stop()



            val intent = Intent(

                context,

                FovixVpnService::class.java

            )



            context.stopService(intent)



        }
        catch(e: Exception) {


            Log.e(

                TAG,

                "STOP ERROR",

                e

            )


        }





        _state.value = VPNState(

            status = ConnectionStatus.DISCONNECTED,

            server = "None"

        )


    }





    fun isRunning(): Boolean {


        return try {


            SingBoxNative.isRunning()


        }
        catch(e: Exception) {


            false


        }


    }



}