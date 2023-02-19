package com.example.pulseapp.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pulseapp.R
import com.example.pulseapp.databinding.AddPulseMeasurementFragmentBinding
import com.example.pulseapp.presentation.model.AddPulseMeasurementEvent
import com.example.pulseapp.presentation.viewModel.AddPulseMeasurementViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddPulseMeasurementFragment :
    BottomSheetDialogFragment(R.layout.add_pulse_measurement_fragment) {

    private val viewModel: AddPulseMeasurementViewModel by viewModel()

    private val binding: AddPulseMeasurementFragmentBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener {
            viewModel.sendEvent(
                AddPulseMeasurementEvent.OnAddMeasurementClick(
                    pulse = binding.pulse.text.toString(),
                    lowerHeartPressure = binding.lowerHeartPressure.text.toString(),
                    upperHeartPressure = binding.upperHeartPressure.text.toString()
                )
            )
            setFragmentResult(UPDATE_DATA_RESULT_KEY, Bundle())
            dismiss()
        }
    }

    companion object {
        const val UPDATE_DATA_RESULT_KEY = "UPDATE_DATA"
    }
}