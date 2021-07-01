package com.example.samplespacex.features.rocket

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.samplespacex.data.SpacexHandlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RocketViewModel @Inject constructor(
    private val repository: SpacexHandlerRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(), LifecycleObserver {
    val rockets = repository.getRockets().asLiveData()
}