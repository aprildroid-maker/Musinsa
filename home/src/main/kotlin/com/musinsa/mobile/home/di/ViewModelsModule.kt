/**
 * @author Daewon on 13,11월,2024
 *
 */

package com.musinsa.mobile.home.di

import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.MavericksViewModelComponent
import com.airbnb.mvrx.hilt.ViewModelKey
import com.musinsa.mobile.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindsViewModelFactory(factory: HomeViewModel.Factory): AssistedViewModelFactory<*, *>
}
