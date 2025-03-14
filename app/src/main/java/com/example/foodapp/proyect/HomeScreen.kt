package com.example.foodapp.proyect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.foodapp.proyect.Category
import com.example.foodapp.proyect.Food
import com.example.foodapp.proyect.Restaurant



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    val categories = remember { listOf(
        Category("Fast Food", "https://www.partstown.com/about-us/wp-content/uploads/2023/11/what-is-considered-fast-food-768x512.jpg"),
        Category("Chinese", "https://assets.epicurious.com/photos/624d9590857fa7e509238b59/1:1/w_960,c_limit/RegionalChinese_HERO_033122_31320.jpg"),
        Category("Italian", "https://static.wixstatic.com/media/2cbff6_ac782b0eaff94ec0881f0299fdb76ab6~mv2.jpg/v1/fill/w_566,h_440,al_c,q_80,usm_0.66_1.00_0.01,enc_avif,quality_auto/2cbff6_ac782b0eaff94ec0881f0299fdb76ab6~mv2.jpg"),
        Category("Mexican", "https://images.immediate.co.uk/production/volatile/sites/30/2022/10/Pork-carnitas-b94893e.jpg?quality=90&webp=true&resize=300,272"),
        Category("Japanese", "https://wandereatwrite.com/usheeche/2021/07/sashimi-min.jpeg")
    ) }

    val restaurants = remember { listOf(
        Restaurant("Burger King", "https://centrosantafe.com.mx/cdn/shop/files/701_medium.png?14707"),
        Restaurant("McDonald's", "https://www.imprentaonline.net/blog/wp-content/uploads/rotulo-mcdonals.png"),
        Restaurant("KFC", "https://1000marcas.net/wp-content/uploads/2020/01/KFC-logo.png"),
        Restaurant("Starbucks", "https://cdn.pixabay.com/photo/2017/09/03/11/05/starbucks-2709927_1280.jpg"),
        Restaurant("Subway", "https://marcasur.com/archivos/destacado_portada/3158.jpg")
    ) }

    val foods = remember { listOf(
        Food("Whopper", "https://media.informabtl.com/wp-content/uploads/2024/10/095f92b0-burger-king-whopper-nuevo-sabor-pan-promocion-e1728675880856-768x594.jpg", 4.5, 99.8),
        Food("Chicken Fries", "https://www.airfryeryum.com/wp-content/uploads/2023/05/IMG_6913-720x961.jpeg", 4.3, 59.8),
        Food("Big Mac", "https://s7d1.scene7.com/is/image/mcdonalds/DC_202302_0005-999_BigMac_1564x1564-1:product-header-desktop?wid=782&hei=782&dpr=off", 4.6, 79.8),
        Food("McFlurry", "https://eldiariony.com/wp-content/uploads/sites/2/2023/08/shutterstock_2163700181.jpg?resize=1316,740&quality=80", 4.7, 49.8),
        Food("Pizza", "https://static.toiimg.com/photo/53110049.cms", 4.8, 120.0),
        Food("Tacos", "https://images.getrecipekit.com/20220622020606-opt_23112018-img_2431.jpeg?aspect_ratio=16:9&quality=90&", 4.9, 39.9)
    ) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp).background(Color.White) )
    {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.AccountCircle, contentDescription = "User", tint = Color.Red, modifier = Modifier.size(32.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Hola, Jorge", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            IconButton(onClick = { /* Logout */ }) {
                Icon(Icons.Default.ExitToApp, contentDescription = "Logout", tint = Color.Red)
            }
        }

        Text("Nuestras categorías", fontSize = 21.sp, fontWeight = FontWeight.Bold)
        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(categories) { CategoryCard(it) }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Busca los mejores restaurantes", fontSize = 21.sp, fontWeight = FontWeight.Bold)
        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(restaurants) { RestaurantCard(it) }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text("Nuestras mejores comidas", fontSize = 21.sp, fontWeight = FontWeight.Bold)
        LazyColumn(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(foods.chunked(2)) { rowFoods ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    rowFoods.forEach { FoodCard(it) }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CategoryCard(category: Category) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            GlideImage(
                model = category.image,
                contentDescription = category.name,
                modifier = Modifier.size(60.dp)
            )
        }
        Text(category.name, fontSize = 14.sp)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RestaurantCard(restaurant: Restaurant) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        GlideImage(
            model = restaurant.image,
            contentDescription = restaurant.name,
            modifier = Modifier.size(100.dp)
        )
        Text(restaurant.name, fontSize = 14.sp)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FoodCard(food: Food) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.size(120.dp)) {
            GlideImage(
                model = food.image,
                contentDescription = food.name,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    text = "$${food.price}",
                    fontSize = 14.sp,
                    color = Color.White,
                    modifier = Modifier
                        .background(Color.Red, shape = CircleShape)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("★ ${food.rating}", fontSize = 14.sp, color = Color.Green)
            Spacer(modifier = Modifier.width(8.dp))
            Text(food.name, fontSize = 14.sp)
        }
    }
}



@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}