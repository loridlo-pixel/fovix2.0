package com.vpn.fovix.core.decision


class DecisionEngine {


    fun evaluate(

        mode: UserMode

    ): DecisionResult {


        return when(mode){


            UserMode.SIMPLE ->

                DecisionResult(

                    recommendedServer = "AUTO",

                    reason = "Automatic optimization",

                    score = 100

                )



            UserMode.ADVANCED ->

                DecisionResult(

                    recommendedServer = "FASTEST",

                    reason = "Speed priority",

                    score = 90

                )



            UserMode.EXPERT ->

                DecisionResult(

                    recommendedServer = "CUSTOM",

                    reason = "Full user control",

                    score = 80

                )

        }

    }


}