package uly.practice.fetchrewards2024.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import uly.practice.fetchrewards2024.data.repository.FetchRewardsRepository
import uly.practice.fetchrewards2024.data.response.FetchResponseItem

import uly.practice.fetchrewards2024.utility.ResourceState
import javax.inject.Inject

@HiltViewModel
class FetchRewardsViewModel @Inject constructor(
    private val fetchRewardsRepository: FetchRewardsRepository
) : ViewModel(){

    private val _data: MutableStateFlow<ResourceState<List<FetchResponseItem>>> = MutableStateFlow(ResourceState.Loading())
    val data : StateFlow<ResourceState<List<FetchResponseItem>>> = _data.asStateFlow()

    init{
        getData()
    }

    private fun sortDataAndRemoveNulls(listFetch : List<FetchResponseItem>): List<FetchResponseItem>{
        Log.i("ViewModel", "hey im sorting")
        return listFetch.filter { !it.name.isNullOrBlank() }.sortedWith(
            compareBy<FetchResponseItem> { it.listId }.thenBy { it.name?.filter { char -> char.isDigit() }
                ?.toIntOrNull() ?: 0 }
        )
    }

    private fun getData(){
        viewModelScope.launch(Dispatchers.IO){
            fetchRewardsRepository.getFetchRewardsData()
                .collectLatest { fetchRewardsResponse ->
                    when(fetchRewardsResponse){
                        is ResourceState.Success -> _data.value = ResourceState.Success(sortDataAndRemoveNulls(fetchRewardsResponse.data))
                        else -> {_data.value = fetchRewardsResponse}
                    }
            }
        }
    }
}