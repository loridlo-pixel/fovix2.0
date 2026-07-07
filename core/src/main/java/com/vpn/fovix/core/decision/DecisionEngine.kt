package com.vpn.fovix.core.decision

class DecisionEngine {


    fun evaluate(
        mode: UserMode
    ): DecisionResult {


        return when(mode) {

            UserMode.SIMPLE ->
                DecisionResult(
                    "AUTO",
                    "Automatic optimization",
                    100
                )


            UserMode.ADVANCED ->
                DecisionResult(
                    "FASTEST",
                    "Speed priority",
                    90
                )


            UserMode.EXPERT ->
                DecisionResult(
                    "CUSTOM",
                    "Full user control",
                    80
                )
        }

    }

}
