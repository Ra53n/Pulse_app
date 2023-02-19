package com.example.pulseapp.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pulseapp.R
import com.example.pulseapp.databinding.FragmentMainBinding
import com.example.pulseapp.presentation.adapter.PulseAdapter
import com.example.pulseapp.presentation.model.MainEvent
import com.example.pulseapp.presentation.view.AddPulseMeasurementFragment.Companion.UPDATE_DATA_RESULT_KEY
import com.example.pulseapp.presentation.viewModel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModel()

    private val binding: FragmentMainBinding by viewBinding()

    private val adapter by lazy { PulseAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        lifecycleScope.launch {
            viewModel.viewStateObserve.collect { state ->
                adapter.setItems(state.pulseMeasurements)
            }
        }

        setFragmentResultListener(UPDATE_DATA_RESULT_KEY)
        { _, _ -> viewModel.sendEvent(MainEvent.ScreenResumed) }
    }

    private fun bindViews() {
        binding.pulseRV.adapter = adapter

        binding.addButton.setOnClickListener {
            AddPulseMeasurementFragment().show(parentFragmentManager, "")
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}