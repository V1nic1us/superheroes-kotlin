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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
                Scaffold(modifier = Modifier.fillMaxSize().padding(WindowInsets.systemBars.asPaddingValues())) { innerPadding ->
                    SuperHeroesApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SuperHeroesApp(modifier: Modifier) {
    LazyColumn(modifier= modifier.fillMaxHeight().background(MaterialTheme.colorScheme.background)) {
        items(items=HeroesRepository.heroes) { hero ->
            CardSuperHeroes(modifier = modifier.padding(8.dp), hero = hero)
        }
    }
}

@Composable
fun CardSuperHeroes(
    modifier: Modifier = Modifier,
    hero: Hero
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .height(104.dp),
        colors =  CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer) ,
    ) {
        Box(modifier= modifier.height(84.dp)){
            Row(modifier= modifier) {
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

@Preview(showBackground = true)
@Composable
fun SuperHeroesAppPreview() {
    SuperheroesTheme {
        SuperHeroesApp(Modifier)
    }
}