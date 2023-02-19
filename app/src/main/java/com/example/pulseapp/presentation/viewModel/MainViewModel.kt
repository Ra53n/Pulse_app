package com.example.pulseapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pulseapp.data.FirestoreRepository
import com.example.pulseapp.presentation.mapper.PulseEntityToUiModelMapper
import com.example.pulseapp.presentation.model.MainEvent
import com.example.pulseapp.presentation.model.MainState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val repository: FirestoreRepository,
    private val mapper: PulseEntityToUiModelMapper
) : ViewModel() {

    private val viewState = MutableStateFlow(MainState())

    val viewStateObserve: StateFlow<MainState>
        get() = viewState

    init {
        loadData()
    }

    fun sendEvent(event: MainEvent) {
        if (event is MainEvent.ScreenResumed) {
            loadData()
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            val measurements = withContext(Dispatchers.IO) { repository.getAllMeasurements() }
            viewState.update { viewState.value.copy(pulseMeasurements = mapper.map(measurements)) }
        }
    }
}