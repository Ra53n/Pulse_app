package com.example.pulseapp.presentation.viewModel

import android.icu.text.SimpleDateFormat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pulseapp.data.FirestoreRepository
import com.example.pulseapp.domain.PulseEntity
import com.example.pulseapp.presentation.model.AddPulseMeasurementEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddPulseMeasurementViewModel(
    val repository: FirestoreRepository
) : ViewModel() {

    fun sendEvent(event: AddPulseMeasurementEvent) {
        if (event is AddPulseMeasurementEvent.OnAddMeasurementClick) {
            addMeasurement(event.pulse, event.lowerHeartPressure, event.upperHeartPressure)
        }
    }

    private fun addMeasurement(
        pulse: String,
        lowerHeartPressure: String,
        upperHeartPressure: String
    ) {
        viewModelScope.launch(context = Dispatchers.IO) {
            val currentDateFormatter = SimpleDateFormat("dd MMMM", Locale.getDefault())
            val currentDate = currentDateFormatter.format(Date())
            val currentTimeFormatter = SimpleDateFormat("HH : mm", Locale.getDefault())
            val currentTime = currentTimeFormatter.format(Date())
            repository.addMeasurement(
                PulseEntity(
                    date = currentDate,
                    pulseMeasurementList = listOf(
                        PulseEntity.PulseMeasurement(
                            time = currentTime,
                            upperHeartPressure = upperHeartPressure,
                            lowerHeartPressure = lowerHeartPressure,
                            pulse = pulse
                        )
                    )
                )
            )
        }
    }
}