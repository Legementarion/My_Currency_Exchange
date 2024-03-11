package com.lego.mycurrencyexchangeapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.asFlow
import com.lego.mycurrencyexchangeapplication.R
import com.lego.mycurrencyexchangeapplication.domain.models.Currency
import com.lego.mycurrencyexchangeapplication.ui.theme.MyCurrencyExchangeApplicationTheme

class MainActivity : ComponentActivity() {

    private val viewModel: CurrencyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.update()
        setContent {
            MyCurrencyExchangeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainCard(viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyCurrencyExchangeApplicationTheme {
        Greeting("Android")
    }
}

@Composable
fun MainCard(viewModel: CurrencyViewModel) {
    Text(
        text = "Exchange rate",
        style = TextStyle(fontSize = 19.sp, color = Color.Black),
        modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 25.dp)
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(5.dp))
    ) {
        Box(modifier = Modifier.background(color = colorResource(id = R.color.teal_200))) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Blue),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Choose the country",
                        style = TextStyle(fontSize = 10.sp, color = Color.White),
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(
                        text = "Select currency",
                        style = TextStyle(fontSize = 10.sp, color = Color.White),
                        modifier = Modifier.padding(
                            start = 10.dp,
                            top = 10.dp,
                            bottom = 10.dp,
                            end = 70.dp
                        )
                    )

                }
                Row(
                    modifier = Modifier
                        .padding(start = 10.dp, bottom = 10.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(Color.Blue),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .clip(
                                RoundedCornerShape(10.dp),
                            )
                    )
                    {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            val dataExample = viewModel.list.asFlow().collectAsState(emptyList())
                            MySearchView(dataExample.value)
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchView(list: List<Currency>) {
    val searchText = remember {
        mutableStateOf("")
    }

    var isActive by remember {
        mutableStateOf(false)
    }
    SearchBar(modifier = Modifier.fillMaxWidth(),
        query = searchText.value,
        onQueryChange = { text ->
            searchText.value = text
//            mainList.value = "Utils.search(text)"
        },
        onSearch = { text ->
            isActive = false

        },
        placeholder = {
            Text(text = "Search", style = TextStyle(color = Color.LightGray))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = "", modifier = Modifier
                    .size(20.dp)
                    .padding(0.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        },
        active = isActive,
        onActiveChange = {
            isActive = it
        }
    ) {
        LazyColumn {
            items(list) { item ->
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
//                        mainList.value = Utils.search(item)
                        isActive = false
                    }
                    .padding(10.dp),
                    contentAlignment = Alignment.Center) {
                    Text(text = item.name, style = TextStyle(color = Color.LightGray))
                }
            }
        }
    }
}