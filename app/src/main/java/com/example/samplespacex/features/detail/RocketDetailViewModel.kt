package com.example.samplespacex.features.detail

import androidx.lifecycle.*
import com.example.samplespacex.data.SpacexHandlerRepository
import com.example.samplespacex.data.model.RocketDetails
import com.example.samplespacex.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RocketDetailViewModel @Inject constructor(
    private val repository: SpacexHandlerRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(), LifecycleObserver {
    fun getRocketDetails(id: String): LiveData<Result<RocketDetails>> {
        return repository.getRocketsDetails(id).asLiveData()
    }
}