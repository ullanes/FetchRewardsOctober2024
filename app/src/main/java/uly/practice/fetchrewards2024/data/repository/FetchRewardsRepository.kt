package uly.practice.fetchrewards2024.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import uly.practice.fetchrewards2024.data.datasource.FetchRewardsDataSource
import uly.practice.fetchrewards2024.data.response.FetchResponseItem
import uly.practice.fetchrewards2024.utility.ResourceState
import javax.inject.Inject

class FetchRewardsRepository @Inject constructor(
    private val fetchRewardsDataSource: FetchRewardsDataSource
) {
    suspend fun getFetchRewardsData() : Flow<ResourceState<List<FetchResponseItem>>>{

        return flow{
            emit(ResourceState.Loading())
            val response = fetchRewardsDataSource.getFetchRewardsData()
            if(response.isSuccessful && response.body() != null){
                emit(ResourceState.Success(response.body()!!))
            }else {
                emit(ResourceState.Error("Error Fetching new Data"))
            }

        }.catch {
            e ->
            emit(ResourceState.Error(e?.localizedMessage ?: "some error inn flow"))

        }
    }
}