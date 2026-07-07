package com.vpn.fovix.app.presentation.decision

import com.vpn.fovix.core.decision.DecisionEngine
import com.vpn.fovix.core.decision.DecisionResult
import com.vpn.fovix.core.decision.UserMode


class DecisionController {


    private val engine = DecisionEngine()


    fun calculate(
        mode: UserMode
    ): DecisionResult {

        return engine.evaluate(mode)

    }

}
