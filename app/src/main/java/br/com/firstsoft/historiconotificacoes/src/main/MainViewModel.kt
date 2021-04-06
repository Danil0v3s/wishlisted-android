package br.com.firstsoft.historiconotificacoes.src.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _isPermissionGiven = MutableStateFlow(false)
    val isPermissionGiven: StateFlow<Boolean>
        get() = _isPermissionGiven

    fun onPermissionChanged(isPermissionGiven: Boolean) {
        _isPermissionGiven.value = isPermissionGiven
    }
}