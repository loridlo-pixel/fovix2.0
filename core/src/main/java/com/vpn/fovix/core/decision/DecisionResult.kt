package com.vpn.fovix.core.decision


data class DecisionResult(

    val recommendedServer: String,

    val reason: String,

    val score: Int

)
