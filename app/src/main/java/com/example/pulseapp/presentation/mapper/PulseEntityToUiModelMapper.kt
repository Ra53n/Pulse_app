package com.example.pulseapp.presentation.mapper

import com.example.pulseapp.domain.PulseEntity
import com.example.pulseapp.presentation.model.PulseUiModel

class PulseEntityToUiModelMapper {
    fun map(entities: List<PulseEntity>): List<PulseUiModel> {
        return entities.map {
            PulseUiModel(
                date = it.date,
                pulseMeasurements = it.pulseMeasurementList.map {
                    PulseUiModel.PulseMeasurementModel(
                        time = it.time,
                        pulse = it.pulse,
                        upperPressure = it.upperHeartPressure,
                        lowerPressure = it.lowerHeartPressure
                    )
                }
            )
        }
    }
}