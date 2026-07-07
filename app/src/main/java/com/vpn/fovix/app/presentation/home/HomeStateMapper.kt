package com.vpn.fovix.app.presentation.home


import com.vpn.fovix.domain.usermode.UserMode



object HomeStateMapper {


    fun mapMode(
        mode: UserMode
    ): HomeVisibility {


        return when(mode){


            UserMode.SIMPLE ->
                HomeVisibility(
                    server=false,
                    metrics=false,
                    expert=false
                )


            UserMode.ADVANCED ->
                HomeVisibility(
                    server=true,
                    metrics=true,
                    expert=false
                )


            UserMode.EXPERT ->
                HomeVisibility(
                    server=true,
                    metrics=true,
                    expert=true
                )

        }


    }


}



data class HomeVisibility(

    val server:Boolean,

    val metrics:Boolean,

    val expert:Boolean

)
