package com.vpn.fovix.app.presentation.home


import androidx.lifecycle.ViewModel
import com.vpn.fovix.core.decision.UserMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class HomeViewModel : ViewModel(){


    private val renderer = HomeModeRenderer()


    private val _state =
        MutableStateFlow(
            renderer.render(UserMode.SIMPLE)
        )


    val state: StateFlow<HomeUiState> = _state



    fun changeMode(
        mode: UserMode
    ){

        _state.value =
            renderer.render(mode)

    }


}
