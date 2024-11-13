/**
 * @author Daewon on 11,11월,2024
 *
 */

package com.musinsa.mobile.home

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.musinsa.mobile.domain.repository.HomeRepository
import com.musinsa.mobile.home.model.HomeUiModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel @AssistedInject constructor(
    @Assisted initialState: HomeUiState,
    private val homeRepository: HomeRepository
) : MavericksViewModel<HomeUiState>(initialState) {
    private var job: Job? = null

    init {
        fetch()
    }

    private fun fetch() {
        if (job?.isActive == true) {
            job?.cancel()
        }

        job = viewModelScope.launch {
            setState { HomeUiState.Loading }
            homeRepository.getHomeList().onSuccess { homeList ->
                val uiModel = homeList.map { HomeUiModel.from(it) }
                setState { HomeUiState.Success(uiModel) }
            }.onFailure {
                setState { HomeUiState.Error }
            }
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<HomeViewModel, HomeUiState> {
        override fun create(state: HomeUiState): HomeViewModel
    }

    companion object : MavericksViewModelFactory<HomeViewModel, HomeUiState> by hiltMavericksViewModelFactory()
}
