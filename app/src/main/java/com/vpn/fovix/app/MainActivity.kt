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



    private val vpnPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->


            Log.e(
                TAG,
                "VPN CALLBACK ${result.resultCode}"
            )


            if (result.resultCode == RESULT_OK) {


                Log.e(
                    TAG,
                    "VPN PERMISSION GRANTED"
                )


                FovixContainer.repository.startVpn()


            }


        }




    override fun onCreate(
        savedInstanceState: Bundle?
    ) {


        super.onCreate(savedInstanceState)


        FovixContainer.init(this)


        checkSingBox()



        setContent {


            FovixTheme {


                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {


                    FovixApp(

                        repository = FovixContainer.repository,


                        onConnect = {

                            requestVpnPermission()

                        },


                        onDisconnect = {

                            FovixContainer.repository.disconnect()

                        }

                    )


                }


            }


        }


    }





    private fun requestVpnPermission() {


        val intent =
            VpnService.prepare(this)


        if (intent != null) {


            Log.e(
                TAG,
                "REQUEST VPN PERMISSION"
            )


            vpnPermissionLauncher.launch(intent)


        } else {


            Log.e(
                TAG,
                "VPN PERMISSION EXISTS"
            )


            FovixContainer.repository.startVpn()


        }


    }





    private fun checkSingBox() {


        try {


            val state =
                SingBoxNative.isRunning()



            Log.i(
                TAG,
                "SINGBOX JNI OK running=$state"
            )


        }
        catch(e: UnsatisfiedLinkError) {


            Log.e(
                TAG,
                "JNI LOAD FAILED",
                e
            )


        }


    }



}