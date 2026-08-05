package com.vpn.fovix.app.presentation.subscription


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.vpn.fovix.data.importer.SubscriptionImportEngine
import com.vpn.fovix.data.subscription.SubscriptionRepository
import com.vpn.fovix.domain.subscription.VpnSubscription

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch

import java.util.UUID



class SubscriptionViewModel(

    private val repository: SubscriptionRepository,

    private val importEngine: SubscriptionImportEngine

) : ViewModel() {



    private val _state =
        MutableStateFlow(
            SubscriptionUiState()
        )


    val state: StateFlow<SubscriptionUiState>
        get() = _state





    init {

        loadSubscriptions()

    }






    private fun loadSubscriptions() {


        _state.value =
            _state.value.copy(

                subscriptions =
                    repository.getSubscriptions()

            )


    }







    fun updateUrl(

        value: String

    ) {


        _state.value =
            _state.value.copy(

                url = value

            )


    }







    fun importSubscription() {


        val url =
            _state.value.url



        if(url.isBlank()) {

            return

        }



        viewModelScope.launch {


            try {


                _state.value =
                    _state.value.copy(

                        isLoading = true,

                        error = null

                    )





                val servers =

                    importEngine.importFromUrl(

                        url

                    )





                val subscription =

                    VpnSubscription(

                        id =
                            UUID.randomUUID()
                                .toString(),


                        name =
                            "VPN Provider",


                        url = url,


                        servers =
                            servers,


                        isActive = true

                    )





                repository.addSubscription(

                    subscription

                )






                _state.value =
                    _state.value.copy(

                        isLoading = false,


                        subscriptions =
                            repository.getSubscriptions(),


                        selected =
                            subscription

                    )




            }
            catch(e: Exception) {


                _state.value =
                    _state.value.copy(

                        isLoading = false,


                        error =
                            e.message
                                ?: "Import failed"

                    )


            }


        }


    }







    fun removeSubscription(

        id: String

    ) {


        repository.removeSubscription(

            id

        )


        loadSubscriptions()


    }







    fun selectSubscription(

        subscription: VpnSubscription

    ) {


        _state.value =

            _state.value.copy(

                selected =
                    subscription

            )


    }



}