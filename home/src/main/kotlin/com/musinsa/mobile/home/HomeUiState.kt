/**
 * @author Daewon on 11,11월,2024
 *
 */

package com.musinsa.mobile.home

import com.airbnb.mvrx.MavericksState
import com.musinsa.mobile.home.model.HomeUiModel


sealed interface HomeUiState : MavericksState {
    data class Loading(
        private val empty: String = ""
    ) : HomeUiState
    data class Error(
        private val empty: String = ""
    ) : HomeUiState
    data class Success(
        val data: List<HomeUiModel>
    ) : HomeUiState
}
