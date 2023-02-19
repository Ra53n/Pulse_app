package com.example.pulseapp.data

import com.example.pulseapp.domain.PulseEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirestoreRepository(
    private val database: FirebaseFirestore
) {

    suspend fun getAllMeasurements() = suspendCoroutine<List<PulseEntity>> { continuation ->
        database.collection(MEASUREMENTS)
            .get()
            .addOnSuccessListener { continuation.resume(it.toObjects(PulseEntity::class.java)) }
            .addOnFailureListener { continuation.resumeWithException(it) }
    }

    suspend fun addMeasurement(pulseEntity: PulseEntity) {
        suspendCoroutine { continuation ->
            database.collection(MEASUREMENTS)
                .add(pulseEntity)
                .addOnSuccessListener { continuation.resume(Unit) }
                .addOnFailureListener { continuation.resumeWithException(it) }
        }
    }

    companion object {
        private const val MEASUREMENTS = "Measurements"
    }
}