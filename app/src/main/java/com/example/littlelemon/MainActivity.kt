package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.ktor.http.ContentType
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittlelemonTheme
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val menuItemsLiveData = MutableLiveData<MenuNetworkData>()
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            val menuItems = getMenu()

            runOnUiThread {
                menuItemsLiveData.value = menuItems
            }
        }
        setContent {
            LittlelemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val responseState = menuItemsLiveData.observeAsState("").value

                   // val navController = rememberNavController()
                  // Navigation(navController = navController)
                    Column {
                        Button(
                            onClick = {
                                lifecycleScope.launch {
                                    val response = getMenu()
                                    runOnUiThread {
                                        menuItemsLiveData.value = response
                                    }
                                }
                            }
                        ) {
                            Text(text = "Download")
                        }

                        Text(text = responseState.toString())
                    }
                }
            }
        }
    }
   /* private suspend fun fetchContent(): String {
        return httpClient
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .bodyAsText()
    }*/
    private suspend fun getMenu(): MenuNetworkData {
        return httpClient
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body()
    }
    /*private suspend fun getMenu(category: String): List<String> {
        val response: Map<String, MenuCategory> =
            client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonMenu.json")
                .body()

        return response[category]?.menu ?: listOf()
    }
    */
}

