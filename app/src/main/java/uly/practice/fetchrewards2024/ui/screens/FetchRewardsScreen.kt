package uly.practice.fetchrewards2024.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import uly.practice.fetchrewards2024.data.response.FetchResponseItem
import uly.practice.fetchrewards2024.ui.components.DisplayListComponent
import uly.practice.fetchrewards2024.ui.components.ErrorStateComponent
import uly.practice.fetchrewards2024.ui.components.Loader
import uly.practice.fetchrewards2024.ui.theme.FetchLightPurple
import uly.practice.fetchrewards2024.utility.ResourceState
import uly.practice.fetchrewards2024.viewmodel.FetchRewardsViewModel

@Composable
fun FetchRewardsScreen(
    fetchRewardsViewModel: FetchRewardsViewModel = hiltViewModel()
){
    val fetchResponse by fetchRewardsViewModel.data.collectAsState()

    Surface(modifier =
    Modifier
        .fillMaxSize()
        .background(
            color = FetchLightPurple
        ).padding(5.dp),
        color = FetchLightPurple
    ) {
        when(fetchResponse){
            is ResourceState.Loading -> {
                Loader()
            }
            is ResourceState.Success -> {
               val response = (fetchResponse as ResourceState.Success<List<FetchResponseItem>>).data.groupBy { it.listId }
                DisplayListComponent(fetchResponseItems = response)
            }
            is ResourceState.Error -> {
                ErrorStateComponent()
            }
        }
    }
}

@Preview
@Composable
fun FetchRewardsScreenPreview(){
    FetchRewardsScreen()
}