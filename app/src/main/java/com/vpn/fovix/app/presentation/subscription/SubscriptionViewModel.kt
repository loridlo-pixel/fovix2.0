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









    fun updateInput(

        value: String

    ) {


        _state.value =
            _state.value.copy(

                inputUrl = value

            )

    }









    fun importSubscription() {


        val url =
            _state.value.inputUrl


        if(url.isBlank())
            return





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

                            extractProviderName(

                                url

                            ),



                        url =

                            url,



                        servers =

                            servers,



                        isActive = true


                    )







                repository.addSubscription(

                    subscription

                )







                _state.value =
                    _state.value.copy(

                        inputUrl = "",

                        subscriptions =
                            repository.getSubscriptions()

                    )




            }
            catch(e: Exception) {



                _state.value =
                    _state.value.copy(

                        error =

                            e.message
                                ?: "Import error"

                    )


            }
            finally {


                _state.value =
                    _state.value.copy(

                        isLoading = false

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









    private fun extractProviderName(

        url: String

    ): String {


        return try {


            val host =

                java.net.URI(

                    url

                )
                    .host



            host
                ?.removePrefix("www.")
                ?: "VPN Provider"


        }
        catch(

            e: Exception

        ) {


            "VPN Provider"


        }


    }



}