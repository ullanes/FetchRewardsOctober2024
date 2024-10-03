package uly.practice.fetchrewards2024.data.datasource

import retrofit2.Response
import uly.practice.fetchrewards2024.data.response.FetchResponseItem


interface FetchRewardsDataSource {
    suspend fun getFetchRewardsData(): Response<List<FetchResponseItem>>
}