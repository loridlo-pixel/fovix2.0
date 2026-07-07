package com.vpn.fovix.app.presentation.home

import com.vpn.fovix.core.decision.UserMode


data class HomeModeTransition(

    val mode: UserMode,

    val showServer: Boolean,

    val showMetrics: Boolean,

    val showExpert: Boolean

)


fun resolveHomeMode(mode: UserMode): HomeModeTransition {

    return when(mode) {


        UserMode.SIMPLE -> {

            HomeModeTransition(

                mode = mode,

                showServer = false,

                showMetrics = false,

                showExpert = false

            )

        }



        UserMode.ADVANCED -> {

            HomeModeTransition(

                mode = mode,

                showServer = true,

                showMetrics = true,

                showExpert = false

            )

        }



        UserMode.EXPERT -> {

            HomeModeTransition(

                mode = mode,

                showServer = true,

                showMetrics = true,

                showExpert = true

            )

        }

    }

}
