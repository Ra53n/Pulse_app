package com.example.pulseapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pulseapp.R
import com.example.pulseapp.databinding.PulseMeasurementItemBinding
import com.example.pulseapp.presentation.model.PulseUiModel

class PulseMeasurementAdapter :
    RecyclerView.Adapter<PulseMeasurementAdapter.PulseMeasurementViewHolder>() {

    private var items: List<PulseUiModel.PulseMeasurementModel> = mutableListOf()

    fun setItems(items: List<PulseUiModel.PulseMeasurementModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PulseMeasurementViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.pulse_measurement_item, parent, false)
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: PulseMeasurementViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class PulseMeasurementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = PulseMeasurementItemBinding.bind(view)
        fun bind(pulseMeasurement: PulseUiModel.PulseMeasurementModel) {
            binding.time.text = pulseMeasurement.time
            binding.pulse.text = pulseMeasurement.pulse
            binding.lowerHeartPressure.text = pulseMeasurement.lowerPressure
            binding.upperHeartPressure.text = pulseMeasurement.upperPressure
        }
    }
}