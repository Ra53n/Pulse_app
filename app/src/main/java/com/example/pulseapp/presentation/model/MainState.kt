package com.example.pulseapp.presentation.model

data class MainState(
    val pulseMeasurements: List<PulseUiModel> = emptyList()
)