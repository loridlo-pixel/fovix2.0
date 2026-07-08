package com.vpn.fovix.vpn.singbox


import android.content.Context
import java.io.File



class SingBoxBinaryManager(
    private val context: Context
) {



    fun getBinaryFile(): File {


        val binary = File(

            context.filesDir,

            "sing-box"

        )


        if(!binary.exists()){


            context.assets.open(

                "singbox/sing-box"

            ).use { input ->


                binary.outputStream().use { output ->


                    input.copyTo(output)


                }


            }


            binary.setExecutable(true)


        }



        return binary


    }


}