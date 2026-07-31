package com.vpn.fovix.app.presentation.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FovixTopBar(
    onAddClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onLogoClick: () -> Unit
) {

    CenterAlignedTopAppBar(

        title = {

            TextButton(
                onClick = onLogoClick
            ){

                Text(
                    text = "FOVIX",
                    style = MaterialTheme.typography.titleLarge
                )

            }

        },


        navigationIcon = {

            IconButton(
                onClick = onAddClick
            ){

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add server"
                )

            }

        },


        actions = {

            IconButton(
                onClick = onSettingsClick
            ){

                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings"
                )

            }

        }
    )
}