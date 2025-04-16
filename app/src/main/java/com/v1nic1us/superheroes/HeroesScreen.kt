package com.v1nic1us.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.v1nic1us.superheroes.model.Hero
import com.v1nic1us.superheroes.model.HeroesRepository
import com.v1nic1us.superheroes.ui.theme.SuperheroesTheme

class HeroesScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperheroesTheme {
                    SuperHeroesApp()
            }
        }
    }
}

@Composable
fun SuperHeroesApp() {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = { TopAppBar() }) { innerPadding ->
        LazyColumn(modifier = Modifier.fillMaxHeight().background(MaterialTheme.colorScheme.background).padding(innerPadding)) {
            items(items = HeroesRepository.heroes) { hero ->
                CardSuperHeroes(modifier = Modifier.padding(8.dp), hero = hero)
            }
        }
    }
}

@Composable
fun CardSuperHeroes(
    modifier: Modifier,
    hero: Hero
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(8.dp),
        colors =  CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer) ,
    ) {
        Box(modifier= Modifier.height(84.dp).padding(8.dp)){
            Row(modifier= Modifier) {
                DescriptionHeroes(
                    nameRes = stringResource(hero.nameRes),
                    descriptionRes = stringResource(hero.descriptionRes)
                )
                Spacer(modifier= modifier.weight(1f))
                IconHeroes(
                    imageResId = hero.imageRes
                )
            }
        }
    }
}

@Composable
fun DescriptionHeroes(
    modifier: Modifier = Modifier,
    nameRes: String,
    descriptionRes: String
) {
    Column(modifier= modifier) {
        Text(
            text = nameRes,
            modifier = modifier,
            style = MaterialTheme.typography.displaySmall,
        )
        Text(
            text = descriptionRes,
            modifier = modifier.width(280.dp),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
fun IconHeroes(
    imageResId: Int
) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = "Superheroes Icon"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge,
            )
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun SuperHeroesAppPreview() {
    SuperheroesTheme {
            SuperHeroesApp()
    }
}