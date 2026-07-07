package com.vpn.fovix.app.presentation.mode

import com.vpn.fovix.domain.usermode.UserMode


class ModeController {


    fun showServerInfo(mode: UserMode): Boolean {

        return when(mode){

            UserMode.SIMPLE ->
                false


            UserMode.ADVANCED ->
                true


            UserMode.EXPERT ->
                true

        }

    }



    fun showAdvancedMetrics(mode: UserMode): Boolean {

        return when(mode){

            UserMode.SIMPLE ->
                false


            UserMode.ADVANCED ->
                true


            UserMode.EXPERT ->
                true

        }

    }



    fun showExpertTools(mode: UserMode): Boolean {

        return mode == UserMode.EXPERT

    }


}
