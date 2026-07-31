package com.vpn.fovix.app.presentation.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FovixTopBar(

    onAddClick: () -> Unit,

    onSettingsClick: () -> Unit,

    onLogoClick: () -> Unit

) {


    CenterAlignedTopAppBar(


        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(

            containerColor = Color(0xFF0B1015),

            titleContentColor = Color.White,

            navigationIconContentColor = Color.White,

            actionIconContentColor = Color.White

        ),


        title = {


            TextButton(

                onClick = onLogoClick

            ) {


                Text(

                    "FOVIX"

                )


            }


        },


        navigationIcon = {


            IconButton(

                onClick = onAddClick

            ) {


                Icon(

                    Icons.Default.Add,

                    null

                )


            }


        },


        actions = {


            IconButton(

                onClick = onSettingsClick

            ) {


                Icon(

                    Icons.Default.Settings,

                    null

                )


            }


        }


    )


}