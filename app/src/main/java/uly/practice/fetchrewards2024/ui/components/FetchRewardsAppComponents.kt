package uly.practice.fetchrewards2024.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uly.practice.fetchrewards2024.R
import uly.practice.fetchrewards2024.data.response.FetchResponseItem
import uly.practice.fetchrewards2024.ui.theme.FetchPurple
import uly.practice.fetchrewards2024.ui.theme.FetchYellow
import uly.practice.fetchrewards2024.ui.theme.Purple40

@Composable
fun Loader(){
    Column ( modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
        horizontalAlignment =  Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(60.dp)
                .padding(10.dp),
            color = FetchPurple
        )
    }
}

@Composable
fun ErrorStateComponent(){
    Column ( modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Image(painter = painterResource(id = (R.drawable.error)), contentDescription = "",
                contentScale = ContentScale.FillBounds)
            Text("Error While Loading Data, Please try again later!", fontSize = 25.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, color = FetchYellow)
    }
}


@Composable
fun DisplayListComponent(fetchResponseItems: Map<Int, List<FetchResponseItem>>) {
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        fetchResponseItems.forEach {( listId, itemsResponse ) ->
            Card(modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(8.dp)
                .border(
                    width = 2.dp, // Thickness of the border
                    color = Color.Black,
                    shape = RoundedCornerShape(8.dp)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ){
                Column(modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = FetchPurple
                    )
                    .padding(2.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(modifier = Modifier.padding(1.dp), text = "Grouped by List id: $listId", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = FetchYellow)
                    LazyRow(modifier = Modifier.padding(2.dp)) {
                        items(itemsResponse){ item ->
                            ItemContainerComponent(item = item)
                        }
                    }

                }
            }
        }
    }

}


@Composable
fun ItemContainerComponent(item: FetchResponseItem){
    Card(modifier = Modifier
        .padding(2.dp)
        .width(125.dp)
        .wrapContentHeight()
        .aspectRatio(1f)
        .border(
            width = 2.dp, // Thickness of the border
            color = FetchYellow,
            shape = RoundedCornerShape(8.dp)
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ){
        Box(modifier = Modifier
            .padding(2.dp)
            .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ){
            Column(modifier = Modifier.padding(2.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Name: " + item.name, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.padding(2.dp))
                Text(text = "Id: " + item.id.toString(), fontSize = 15.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
@Preview
fun PreviewDisplayListComponents(){
    ErrorStateComponent()
}