package com.musinsa.mobile.data

import com.musinsa.mobile.data.datasource.DefaultHomeDataSource
import com.musinsa.mobile.data.datasource.HomeDataSource
import com.musinsa.mobile.data.service.HomeService
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class DataSourceTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var homeService: HomeService

    private lateinit var homeDataSource: HomeDataSource

    @Before
    fun setUp() {
        hiltRule.inject()
        homeDataSource = DefaultHomeDataSource(homeService)
    }

    @Test
    fun `정상적으로_홈_데이터를_호출한다`() = runTest {
        val result = homeDataSource.getHomeList()
        assertThat(result.isSuccess).isEqualTo(true)
    }
}
