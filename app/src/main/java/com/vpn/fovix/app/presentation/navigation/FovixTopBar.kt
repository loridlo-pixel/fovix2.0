package com.vpn.fovix.app.presentation.navigation


import androidx.compose.foundation.layout.Box

import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.graphics.Color


import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Plus
import com.composables.icons.lucide.Settings



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FovixTopBar(


    onAddSubscription: () -> Unit,


    onPasteClipboard: () -> Unit,


    onQrScan: () -> Unit,


    onSettingsClick: () -> Unit,


    onLogoClick: () -> Unit


) {


    var menuExpanded by remember {


        mutableStateOf(false)


    }



    CenterAlignedTopAppBar(



        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(


            containerColor = Color(0xFF0B1015),


            titleContentColor = Color.White,


            actionIconContentColor = Color.White,


            navigationIconContentColor = Color.White


        ),




        title = {


            TextButton(

                onClick = onLogoClick

            ){


                Text(

                    text = "FOVIX",


                    color = Color.White


                )


            }


        },





        navigationIcon = {


            Box {


                IconButton(


                    onClick = {


                        menuExpanded = true


                    }


                ){



                    Icon(


                        imageVector = Lucide.Plus,


                        contentDescription = "Add"


                    )


                }




                DropdownMenu(


                    expanded = menuExpanded,


                    onDismissRequest = {


                        menuExpanded = false


                    }


                ){



                    DropdownMenuItem(


                        text = {


                            Text(
                                "Add subscription"
                            )


                        },


                        onClick = {


                            menuExpanded = false

                            onAddSubscription()


                        }


                    )




                    DropdownMenuItem(


                        text = {


                            Text(
                                "Paste clipboard"
                            )


                        },


                        onClick = {


                            menuExpanded = false

                            onPasteClipboard()


                        }


                    )




                    DropdownMenuItem(


                        text = {


                            Text(
                                "Scan QR"
                            )


                        },


                        onClick = {


                            menuExpanded = false

                            onQrScan()


                        }


                    )


                }


            }


        },




        actions = {



            IconButton(


                onClick = onSettingsClick


            ){



                Icon(


                    imageVector = Lucide.Settings,


                    contentDescription = "Settings"


                )


            }



        }



    )


}