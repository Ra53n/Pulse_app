package com.example.pulseapp.presentation.model

class PulseUiModel(
    val date: String,
    val pulseMeasurements: List<PulseMeasurementModel>
) {

    class PulseMeasurementModel(
        val time: String,
        val upperPressure: String,
        val lowerPressure: String,
        val pulse: String
    )
}