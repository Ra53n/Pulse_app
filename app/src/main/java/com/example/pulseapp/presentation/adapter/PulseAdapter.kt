package com.example.pulseapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pulseapp.R
import com.example.pulseapp.databinding.PulseItemBinding
import com.example.pulseapp.presentation.model.PulseUiModel

class PulseAdapter : RecyclerView.Adapter<PulseAdapter.PulseViewHolder>() {

    private var items: List<PulseUiModel> = mutableListOf()

    fun setItems(items: List<PulseUiModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PulseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.pulse_item, parent, false)
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: PulseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class PulseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = PulseItemBinding.bind(view)
        fun bind(pulse: PulseUiModel) {
            val adapter = PulseMeasurementAdapter()
            binding.date.date.text = pulse.date
            binding.pulseMeasurementRV.adapter = adapter
            adapter.setItems(pulse.pulseMeasurements)
        }
    }
}