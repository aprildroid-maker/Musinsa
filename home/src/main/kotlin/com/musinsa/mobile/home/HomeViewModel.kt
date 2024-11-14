/**
 * @author Daewon on 11,11월,2024
 *
 */

package com.musinsa.mobile.home

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.musinsa.mobile.domain.model.ContentType
import com.musinsa.mobile.domain.model.Footer
import com.musinsa.mobile.domain.model.FooterType
import com.musinsa.mobile.domain.model.Home
import com.musinsa.mobile.domain.repository.HomeRepository
import com.musinsa.mobile.home.model.ContentUiModel
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
    private val networkData = mutableListOf<Home>()

    init {
        fetch()
    }

    private fun fetch() {
        if (job?.isActive == true) {
            job?.cancel()
        }

        networkData.clear()
        job = viewModelScope.launch {
            setState { HomeUiState.Loading() }
            homeRepository.getHomeList().onSuccess { homeList ->
                networkData.addAll(homeList)
                val uiModel = homeList.map { HomeUiModel.from(it) }
                setState { HomeUiState.Success(uiModel) }
            }.onFailure {
                setState { HomeUiState.Error() }
            }
        }
    }

    fun fetch(index: Int) {
        withState { state ->
            if (state is HomeUiState.Success) {
                val uiModel = state.data.getOrNull(index)
                var needMore = uiModel?.footer
                if (uiModel != null) {
                    val networkContents = networkData[index].contents
                    val newData = when (uiModel.footer?.type) {
                        FooterType.MORE -> {
                            when (uiModel.type) {
                                ContentType.GRID -> {
                                    needMore = updateNeedMore(networkContents?.goods?.size, uiModel.contents.size, needMore)
                                    networkContents?.goods?.take(uiModel.contents.size + 3)?.map { ContentUiModel.fromDomainModel(it) }
                                }

                                ContentType.STYLE -> {
                                    needMore = updateNeedMore(networkContents?.styles?.size, uiModel.contents.size, needMore)
                                    networkContents?.styles?.take(uiModel.contents.size + 3)?.map { ContentUiModel.fromDomainModel(it) }
                                }
                                else -> null
                            }
                        }

                        FooterType.REFRESH -> {
                            uiModel.contents.shuffled()
                        }

                        else -> {
                            uiModel.contents
                        }
                    }

                    if (newData != null) {
                        updateStateWithNewData(state, index, uiModel, newData, needMore)
                    }
                }
            }
        }
    }

    private fun updateNeedMore(
        originDataSize: Int?,
        currentContentSize: Int,
        footer: Footer?
    ): Footer? {
        return originDataSize?.minus(currentContentSize.plus(3))?.let {
            if (it > 0) {
                footer
            } else {
                null
            }
        }
    }

    private fun updateStateWithNewData(
        state: HomeUiState.Success,
        index: Int,
        uiModel: HomeUiModel,
        newData: List<ContentUiModel?>,
        needMore: Footer?
    ) {
        setState {
            HomeUiState.Success(
                state.data.toMutableList().apply {
                    set(index, uiModel.copy(contents = newData, footer = needMore))
                }
            )
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<HomeViewModel, HomeUiState> {
        override fun create(state: HomeUiState): HomeViewModel
    }

    companion object :
        MavericksViewModelFactory<HomeViewModel, HomeUiState> by hiltMavericksViewModelFactory() {
        override fun initialState(viewModelContext: ViewModelContext): HomeUiState {
            return HomeUiState.Loading()
        }
    }
}
