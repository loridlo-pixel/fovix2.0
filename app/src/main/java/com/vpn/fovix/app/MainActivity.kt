package com.vpn.fovix.app


import android.net.VpnService
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.vpn.fovix.app.presentation.FovixApp
import com.vpn.fovix.app.presentation.theme.FovixTheme
import com.vpn.fovix.vpn.SingBoxNative


class MainActivity : ComponentActivity() {


    companion object {

        private const val TAG = "FOVIX_MAIN"

    }



    private lateinit var appContainer: AppContainer



    private val vpnPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {


            if (it.resultCode == RESULT_OK) {


                Log.e(
                    TAG,
                    "VPN PERMISSION GRANTED"
                )


                appContainer
                    .vpnRepository
                    .startVpn()


            }


        }





    override fun onCreate(
        savedInstanceState: Bundle?
    ) {


        super.onCreate(savedInstanceState)



        appContainer =
            (application as FovixApplication)
                .container



        checkSingBox()



        setContent {


            FovixTheme {


                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {


                    FovixApp(

                        repository =
                            appContainer.vpnRepository,


                        onConnect = {

                            requestVpnPermission()

                        },


                        onDisconnect = {


                            appContainer
                                .vpnRepository
                                .disconnect()


                        }


                    )


                }


            }


        }


    }







    private fun requestVpnPermission() {


        val intent =
            VpnService.prepare(this)



        if(intent != null){


            vpnPermissionLauncher.launch(intent)


        }
        else {


            appContainer
                .vpnRepository
                .startVpn()


        }


    }








    private fun checkSingBox(){


        try {


            val state =
                SingBoxNative.isRunning()



            Log.i(
                TAG,
                "SINGBOX JNI OK running=$state"
            )


        }
        catch(e: Exception){


            Log.e(
                TAG,
                "JNI ERROR",
                e
            )


        }


    }


}