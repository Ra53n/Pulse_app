package com.example.pulseapp.domain

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class PulseEntity(
    val date: String = "",
    val pulseMeasurementList: List<PulseMeasurement> = emptyList()
) : Serializable {
    @Keep
    class PulseMeasurement(
        val time: String = "",
        val upperHeartPressure: String = "",
        val lowerHeartPressure: String = "",
        val pulse: String = ""
    ) : Serializable
}