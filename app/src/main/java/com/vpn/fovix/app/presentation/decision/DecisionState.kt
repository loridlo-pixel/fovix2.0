package com.vpn.fovix.app.presentation.decision


data class DecisionState(

    val server: String = "AUTO",

    val description: String = "",

    val score: Int = 0

)
