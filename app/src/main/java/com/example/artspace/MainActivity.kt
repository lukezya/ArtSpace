package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = Color(0xFF185F5F)
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun Artwork(title: String, artist: String, year: Int, @DrawableRes artworkId: Int) {
    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Surface(
            elevation = 20.dp,
            border = BorderStroke(8.dp, Color.White),
            modifier = Modifier.padding(12.dp)
        ) {
            Image(painter = painterResource(id = artworkId), contentDescription = null)
        }

        Spacer(modifier = Modifier.height(15.dp))
        Surface(
            elevation = 20.dp,
            border = BorderStroke(8.dp, Color.White),
            modifier = Modifier.padding(12.dp),
            color = Color.White
        ) {
            Column(modifier = Modifier.wrapContentWidth(), horizontalAlignment = Alignment.End) {
                Text(
                    text = title,
                    fontSize = 28.sp,
                    modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp),
                    color = Color.Black
                )
                Row {
                    Text(
                        text = artist,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = " ($year)",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 10.dp, end = 10.dp),
                        color = Color.Black
                    )
                }

            }

        }

    }
}

@Composable
fun Navigation(previousClick: () -> Unit, nextClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(
            onClick = previousClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFDD7718))
        ) {
            Text(
                text = "Previous",
                modifier = Modifier.width(65.dp),
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
        Button(
            onClick = nextClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFDD7718))
        ) {
            Text(
                text = "Next",
                modifier = Modifier.width(65.dp),
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }

}

@Composable
fun Style(modifier: Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth(0.6f)
            .height(10.dp),
        color = Color(0xFFDD7718),
    ) {}
}

@Preview
@Composable
fun ArtSpaceApp() {
    var artworkNo by remember {
        mutableStateOf(1)
    }

    val year = 2023
    val artist = "Tony Guo"

    var title: String
    var artworkId: Int

    when (artworkNo) {
        1 -> {
            title = stringResource(R.string.samurai_pond)
            artworkId = R.drawable.samurai_pond
        }
        2 -> {
            title = stringResource(R.string.white_hair_girl)
            artworkId = R.drawable.white_hair_girl
        }
        3 -> {
            title = stringResource(R.string.piano_cloud)
            artworkId = R.drawable.piano_cloud
        }
        4 -> {
            title = stringResource(R.string.boulder_beach)
            artworkId = R.drawable.boulder_beach
        }
        5 -> {
            title = stringResource(R.string.universe_girl)
            artworkId = R.drawable.beauty_girl
        }
        else -> {
            title = stringResource(R.string.little_shop)
            artworkId = R.drawable.lil_shop
        }

    }

    Surface(
        modifier = Modifier.fillMaxSize(), color = Color(0xFF185F5F)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Style(modifier = Modifier.align(Alignment.Start))
            Spacer(modifier = Modifier.height(60.dp))
            Artwork(
                title = title, artist = artist, year = year, artworkId = artworkId
            )
            Spacer(modifier = Modifier.height(60.dp))
            Style(modifier = Modifier.align(Alignment.End))
            Spacer(modifier = Modifier.height(120.dp))
            Navigation({ if (artworkNo > 1) artworkNo-- else artworkNo = 6 },
                { if (artworkNo < 6) artworkNo++ else artworkNo = 1 })
        }
    }

}
