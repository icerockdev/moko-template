package org.example.library

import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class StateFlowViewModel : ViewModel() {
    @NativeCoroutineScope
    internal val coroutineScope = viewModelScope

    val login: MutableStateFlow<String> = MutableStateFlow("")
    val password: MutableStateFlow<String> = MutableStateFlow("")

    val isSubmitEnabled: StateFlow<Boolean> = combine(login, password) { login, password ->
        login.isNotBlank() && password.isNotBlank()
    }.stateIn(viewModelScope, SharingStarted.Lazily, initialValue = false)
}
