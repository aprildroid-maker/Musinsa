/**
 * @author Daewon on 11,11월,2024
 *
 */

package com.musinsa.mobile.home

import com.airbnb.mvrx.MavericksState
import com.musinsa.mobile.home.model.HomeUiModel


sealed interface HomeUiState : MavericksState {
    data object Loading : HomeUiState
    data object Error : HomeUiState
    data class Success(
        val data: List<HomeUiModel>
    ) : HomeUiState
}
