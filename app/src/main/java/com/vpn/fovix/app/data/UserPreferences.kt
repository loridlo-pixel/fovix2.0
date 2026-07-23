package com.vpn.fovix.app.data


import android.content.Context

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey

import androidx.datastore.preferences.preferencesDataStore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


import com.vpn.fovix.app.presentation.home.UserMode




private val Context.dataStore by preferencesDataStore(

    name = "fovix_preferences"

)





class UserPreferences(


    private val context: Context


) {



    companion object {


        private val USER_MODE =

            stringPreferencesKey(

                "user_mode"

            )


    }







    val userMode: Flow<UserMode> =


        context.dataStore.data.map {


            val value =

                it[USER_MODE]

                    ?: UserMode.SIMPLE.name




            try {


                UserMode.valueOf(

                    value

                )


            }
            catch(e: Exception) {


                UserMode.SIMPLE


            }



        }









    suspend fun setUserMode(


        mode: UserMode


    ) {



        context.dataStore.edit {


            it[USER_MODE] =

                mode.name


        }


    }




}