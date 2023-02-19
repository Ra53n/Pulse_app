package com.example.pulseapp.data.di

import com.example.pulseapp.data.FirestoreRepository
import com.example.pulseapp.presentation.mapper.PulseEntityToUiModelMapper
import com.example.pulseapp.presentation.viewModel.AddPulseMeasurementViewModel
import com.example.pulseapp.presentation.viewModel.MainViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        FirebaseFirestore.getInstance().apply {
            firestoreSettings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(false)
                .build()
        }
    }
    factory { FirestoreRepository(get()) }
    factory { PulseEntityToUiModelMapper() }
    viewModel { MainViewModel(get(), get()) }
    viewModel { AddPulseMeasurementViewModel(get()) }
}