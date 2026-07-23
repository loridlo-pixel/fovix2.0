package com.vpn.fovix.app.presentation.subscription


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.vpn.fovix.data.importer.SubscriptionImportEngine
import com.vpn.fovix.data.repository.ServerRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch



class SubscriptionViewModel(

    private val importEngine: SubscriptionImportEngine,

    private val serverRepository: ServerRepository

) : ViewModel() {



    private val _state =

        MutableStateFlow(

            SubscriptionUiState()

        )



    val state: StateFlow<SubscriptionUiState> =

        _state





    fun updateInput(

        value: String

    ) {


        _state.value =

            _state.value.copy(

                input = value

            )


    }





    fun importSubscription() {



        val source =

            _state.value.input.trim()



        if(source.isEmpty()) {


            _state.value =

                _state.value.copy(

                    message =
                        "Введите ссылку или конфиг"

                )


            return

        }





        viewModelScope.launch {



            try {



                _state.value =

                    _state.value.copy(

                        loading = true,

                        message = ""

                    )





                val imported =

                    importEngine.import(

                        source

                    )





                imported.forEach {



                    serverRepository.addServer(

                        it

                    )


                }





                _state.value =

                    _state.value.copy(

                        loading = false,

                        servers = imported,

                        message =
                            "Импортировано: ${imported.size}"

                    )



            }

            catch(e: Exception) {



                _state.value =

                    _state.value.copy(

                        loading = false,

                        message =
                            e.message
                                ?: "Ошибка импорта"

                    )


            }



        }


    }



}