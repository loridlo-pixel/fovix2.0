package com.vpn.fovix.app.presentation.home


import com.vpn.fovix.core.decision.UserMode


class HomeModeRenderer {


    fun render(
        mode: UserMode
    ): HomeUiState {


        return when(mode){


            UserMode.SIMPLE ->
                HomeUiState(
                    mode = mode,
                    title = "Simple Mode",
                    showServerInfo = false,
                    showExpertSettings = false
                )


            UserMode.ADVANCED ->
                HomeUiState(
                    mode = mode,
                    title = "Advanced Mode",
                    showServerInfo = true,
                    showExpertSettings = false
                )


            UserMode.EXPERT ->
                HomeUiState(
                    mode = mode,
                    title = "Expert Mode",
                    showServerInfo = true,
                    showExpertSettings = true
                )

        }

    }

}
