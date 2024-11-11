/**
 * @author Daewon on 11,11월,2024
 *
 */

package com.musinsa.mobile.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musinsa.mobile.domain.repository.HomeRepository
import com.musinsa.mobile.home.model.HomeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    val homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    private var job: Job? = null

    init {
        fetch()
    }

    private fun fetch() {
        if (job?.isActive == true) {
            job?.cancel()
        }

        job = viewModelScope.launch {
            homeUiState.value = HomeUiState.Loading
            homeRepository.getHomeList().onSuccess { homeList ->
                val uiModel = homeList.map { HomeUiModel.from(it) }
                homeUiState.value = HomeUiState.Success(uiModel)
            }.onFailure {
                homeUiState.value = HomeUiState.Error
            }
        }
    }
}
