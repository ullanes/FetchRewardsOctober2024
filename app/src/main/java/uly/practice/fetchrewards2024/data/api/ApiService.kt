package uly.practice.fetchrewards2024.data.api

import retrofit2.Response
import retrofit2.http.GET
import uly.practice.fetchrewards2024.data.response.FetchResponseItem


interface ApiService {
    @GET("hiring.json")
    suspend fun getFetchRewardsData() : Response<List<FetchResponseItem>>
}