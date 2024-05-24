package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import androidx.compose.runtime.getValue

@Composable
fun Home(navController: NavHostController, database: LittleLemonDatabase) {
    val searchPhrase = remember {
        mutableStateOf("")
    }
    val menu by database.menuDao().getItems().observeAsState(initial = emptyList())
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon logo",
                modifier = Modifier
                    .height(100.dp)
                    .padding(20.dp)
                    .weight(1.0f),
                alignment = Alignment.TopStart
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile button",
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .padding(20.dp)
                    .clickable { navController.navigate(Screens.Profile.rout) },
                alignment = Alignment.TopEnd
            )
        }
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Hero(searchPhrase = searchPhrase)
            MenuItems(data = filterSearch(searchPhrase, menu))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Hero(searchPhrase: MutableState<String>) {
    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.primary_green))
            .padding(20.dp)
    ) {
        Text(
            text = "Little Lemon",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            color = colorResource(id = R.color.primary_yellow),
            fontSize = 48.sp
        )
        Row(verticalAlignment = Alignment.Bottom) {
            Column(
                modifier = Modifier.weight(0.66f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Chicago",
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = colorResource(
                        id = R.color.highlight_light
                    ),
                    fontSize = 30.sp
                )
                Text(
                    text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                    modifier = Modifier.padding(end = 20.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Normal
                )
            }
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Hero image",
                modifier = Modifier
                    .weight(0.34f)
                    .clip(shape = RoundedCornerShape(16.dp))
            )
        }
        TextField(
            value = searchPhrase.value,
            onValueChange = { searchPhrase.value = it },
            placeholder = { Text(text = "Enter Search Phrase") },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(data: List<MenuItemDatabase>) {
    val category = remember {
        mutableStateOf("")
    }

    val categorized = filterCategory(category, data)

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
            .horizontalScroll(
                rememberScrollState()
            )
            .padding(20.dp)
    ) { //Chips are also an option
        Button(
            onClick = { category.value = if (category.value == "starters") "" else "starters" },
            modifier = Modifier
                .padding(end = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.highlight_light),
                contentColor = colorResource(id = R.color.highlight_dark)
            ), shape = RoundedCornerShape(16.dp)
        ) {
            if (category.value == "starters")
                Icon(
                    painter = painterResource(id = R.drawable.baseline_check_circle_outline_24),
                    contentDescription = "Selected",
                    modifier = Modifier.padding(end = 8.dp)
                )
            Text(text = "Starters", fontWeight = FontWeight.ExtraBold)
        }
        Button(
            onClick = { category.value = if (category.value == "mains") "" else "mains" },
            modifier = Modifier
                .padding(end = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.highlight_light),
                contentColor = colorResource(id = R.color.highlight_dark)
            ), shape = RoundedCornerShape(16.dp)
        ) {
            if (category.value == "mains")
                Icon(
                    painter = painterResource(id = R.drawable.baseline_check_circle_outline_24),
                    contentDescription = "Selected",
                    modifier = Modifier.padding(end = 8.dp)
                )
            Text(text = "Mains", fontWeight = FontWeight.ExtraBold)
        }
        Button(
            onClick = { category.value = if (category.value == "desserts") "" else "desserts" },
            modifier = Modifier
                .padding(end = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.highlight_light),
                contentColor = colorResource(id = R.color.highlight_dark)
            ), shape = RoundedCornerShape(16.dp)
        ) {
            if (category.value == "desserts")
                Icon(
                    painter = painterResource(id = R.drawable.baseline_check_circle_outline_24),
                    contentDescription = "Selected",
                    modifier = Modifier.padding(end = 8.dp)
                )
            Text(text = "Desserts", fontWeight = FontWeight.ExtraBold)
        }
        Button(
            onClick = { category.value = if (category.value == "drinks") "" else "drinks" },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.highlight_light),
                contentColor = colorResource(id = R.color.highlight_dark)
            ), shape = RoundedCornerShape(16.dp)
        ) {
            if (category.value == "drinks")
                Icon(
                    painter = painterResource(id = R.drawable.baseline_check_circle_outline_24),
                    contentDescription = "Selected",
                    modifier = Modifier.padding(end = 8.dp)
                )
            Text(text = "Drinks", fontWeight = FontWeight.ExtraBold)
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()) {
        for (index in categorized.indices){
            var item = categorized[index]
            Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.Bottom) {
                Column(modifier = Modifier.weight(0.66f)) {
                    Text(text = item.title, fontWeight = FontWeight.Bold)
                    Text(
                        text = item.description,
                        color = colorResource(id = R.color.primary_green),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 24.sp,
                        modifier = Modifier.padding(vertical = 8.dp),
                        maxLines = 2
                    )
                    Text(
                        text = "$${item.price}",
                        color = colorResource(id = R.color.primary_green),
                        fontSize = 16.sp
                    )
                }
                GlideImage(
                    model = item.image.toUri(),
                    contentDescription = "A picture of the dish.",
                    modifier = Modifier
                        .weight(0.34f)
                        .clip(shape = RoundedCornerShape(16.dp))
                )
            }
        }
    }
}



fun filterSearch(
    searchPhrase: MutableState<String>,
    menu: List<MenuItemDatabase>
): List<MenuItemDatabase> {
    return if (searchPhrase.value.isBlank()) {
        menu
    } else {
        menu.filter {
            it.title.contains(searchPhrase.value, true)
        }
    }
}

fun filterCategory(category: MutableState<String>, data: List<MenuItemDatabase>): List<MenuItemDatabase> {
    return if (category.value.isBlank()) {
        data
    } else {
        data.filter { it.category == category.value }
    }
}
