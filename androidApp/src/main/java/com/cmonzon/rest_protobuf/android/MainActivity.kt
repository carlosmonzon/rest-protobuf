package com.cmonzon.rest_protobuf.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmonzon.apimodels.UpcomingMoviesDto
import com.cmonzon.data.ApiResponse
import com.cmonzon.data.TheMovieDbApi
import com.cmonzon.rest_protobuf.Greeting
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val api = TheMovieDbApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    GreetingView(Greeting().greet())
                }
            }
        }
    }
}

val api = TheMovieDbApi()

@Composable
fun GreetingView(text: String) {
    Column(Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
        Text(text = text)
        val upcoming = remember {
            mutableStateOf<UpcomingMoviesDto?>(null)
        }
        val errorMessage = remember { mutableStateOf("") }
        LaunchedEffect(api) {
            val response = api.getUpcoming()
            when (response) {
                is ApiResponse.Error.HttpError -> errorMessage.value = "Error $response.code"
                ApiResponse.Error.NetworkError -> errorMessage.value = "Internet error"
                ApiResponse.Error.SerializationError -> errorMessage.value = "serialisation error"
                ApiResponse.Error.UnauthorizedException -> errorMessage.value = "Unauthorised"
                is ApiResponse.Success -> {
                    upcoming.value = response.body
                }
            }
        }

        Text(
            text = errorMessage.value
        )

        if (upcoming.value != null) {
            val list = upcoming.value!!.results
            LazyColumn {
                items(list) { item ->
                    Text(
                        text = item.overview,
                        modifier = Modifier.padding(vertical = 10.dp),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
