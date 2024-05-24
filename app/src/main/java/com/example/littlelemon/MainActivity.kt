package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.LittleLemonDatabase.Companion.getDatabase
import com.example.littlelemon.ui.theme.LittlelemonTheme

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Little_LemonTheme
class MainActivity : ComponentActivity() {
    private val httpClient = HttpClient(Android){
        install(ContentNegotiation){
            json(contentType = ContentType("text", "plain"))
        }
    }
    private val database by lazy { getDatabase(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LittlelemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navHostController = rememberNavController()
                    val prefs = getSharedPreferences("LittleLemon", MODE_PRIVATE)

                    Navigation(navController = navHostController,prefs, database = database)
                }
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            if(database.menuDao().isEmpty()) {
                saveMenuToDatabase(fetchMenu())
            }
        }
    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        val url = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
        val response = httpClient.get(url).body<MenuNetworkData>()
        return response.menu
    }

    private suspend fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuDao().insertItems(menuItemsRoom)
    }


}


