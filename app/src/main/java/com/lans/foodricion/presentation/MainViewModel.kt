package com.lans.foodricion.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.usecase.IsAuthenticatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val isAuthenticatedUseCase: IsAuthenticatedUseCase
): ViewModel() {
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: Flow<Boolean> get() = _isAuthenticated
    var splashState by mutableStateOf(true)

    init {
        viewModelScope.launch {
            isAuthenticatedUseCase.invoke().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _isAuthenticated.value = response.data
                        splashState = false
                    }

                    is Resource.Error -> {
                        _isAuthenticated.value = false
                    }

                    else -> Unit
                }
            }
        }
    }
}