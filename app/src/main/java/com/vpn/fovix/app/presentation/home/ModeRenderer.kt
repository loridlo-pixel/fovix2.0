package com.vpn.fovix.app.presentation.home


import androidx.compose.runtime.Composable
import com.vpn.fovix.domain.usermode.UserMode



@Composable
fun ModeRenderer(
mode: UserMode
){


when(mode){


UserMode.SIMPLE -> {

SimpleView()

}


UserMode.ADVANCED -> {

AdvancedView()

}


UserMode.EXPERT -> {

ExpertView()

}


}


}

