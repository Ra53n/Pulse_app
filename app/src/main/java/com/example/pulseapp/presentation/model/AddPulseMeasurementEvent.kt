package com.example.pulseapp.presentation.model

sealed class AddPulseMeasurementEvent {
    class OnAddMeasurementClick(
        val pulse: String,
        val lowerHeartPressure: String,
        val upperHeartPressure: String
    ) : AddPulseMeasurementEvent()
}