package com.vpn.fovix.app.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable


@Composable
fun HomeAnimatedSection(
    visible: Boolean,
    content: @Composable () -> Unit
) {

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut()
    ) {

        content()

    }

}
