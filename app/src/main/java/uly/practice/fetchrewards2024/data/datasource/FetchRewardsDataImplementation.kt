package uly.practice.fetchrewards2024.data.datasource

import android.util.Log
import retrofit2.Response
import uly.practice.fetchrewards2024.data.api.ApiService
import uly.practice.fetchrewards2024.data.response.FetchResponseItem
import javax.inject.Inject

class FetchRewardsDataImplementation @Inject constructor(
    private val apiService: ApiService
) : FetchRewardsDataSource {
    override suspend fun getFetchRewardsData(): Response<List<FetchResponseItem>> {
        return apiService.getFetchRewardsData()
    }
}