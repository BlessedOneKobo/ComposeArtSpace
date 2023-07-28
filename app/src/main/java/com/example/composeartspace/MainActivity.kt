package com.example.composeartspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeartspace.ui.theme.ComposeArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun DisplayControllerButton(
    @StringRes textId: Int, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    Button(onClick = onClick, modifier = modifier.width(128.dp)) {
        Text(text = stringResource(id = textId))
    }
}

@Composable
fun DisplayController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        DisplayControllerButton(
            textId = R.string.previous_button_text,
            onClick = onPreviousClick
        )
        DisplayControllerButton(
            textId = R.string.next_button_text,
            onClick = onNextClick
        )
    }
}

@Composable
fun ArtDescriptor(
    name: String, artist: String, year: Int, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(
                start = 16.dp,
                top = 20.dp,
                bottom = 20.dp,
                end = 64.dp,
            )
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Light,
        )
        Spacer(modifier = Modifier.size(4.dp))
        Row {
            Text(text = artist, style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "($year)",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Light
            )
        }
    }
}

@Composable
fun ArtworkWall(
    @DrawableRes painterId: Int,
    painterDescription: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(360.dp),
        shadowElevation = 12.dp,
    ) {
        Image(
            painter = painterResource(id = painterId),
            contentDescription = painterDescription,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.padding(all = 32.dp),
        )
    }
}

data class GalleryImage(
    val name: String,
    val artist: String,
    val year: Int,
    @DrawableRes val painterId: Int
)

@Composable
fun ComposeArtSpaceApp() {
    val images = listOf(
        GalleryImage(
            name = "Samurai Fighting the Waves",
            artist = "HUHSOO",
            year = 2022,
            painterId = R.drawable.wallhaven_72rxqo
        ),
        GalleryImage(
            name = "A City at Sunset",
            artist = "Yu jing",
            year = 2022,
            painterId = R.drawable.wallhaven_zyxvqy,
        ),
        GalleryImage(
            name = "Still Life of Blue Rose and Other Flowers",
            artist = "Surendra Rajawat",
            year = 2022,
            painterId = R.drawable.wallhaven_k7q9m7,
        )
    )
    var index by remember { mutableStateOf(0) }

    val imageOnDisplay = images[index]
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        ArtworkWall(
            painterId = imageOnDisplay.painterId,
            painterDescription = imageOnDisplay.name,
            modifier = Modifier
                .padding(bottom = 52.dp)
        )
        Column {
            ArtDescriptor(
                name = imageOnDisplay.name,
                artist = imageOnDisplay.artist,
                year = imageOnDisplay.year,
            )
            Spacer(modifier = Modifier.size(8.dp))
            DisplayController(
                onPreviousClick = {
                    index = if (index == 0) images.size - 1 else index - 1
                },
                onNextClick = { index = (index + 1) % images.size },
                modifier = Modifier.padding(
                    start = 12.dp,
                    end = 12.dp
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ComposeArtSpaceTheme {
        ComposeArtSpaceApp()
    }
}

@Preview(showBackground = true)
@Composable
fun ArtworkWallPreview() {
    ComposeArtSpaceTheme {
        ArtworkWall(
            painterId = R.drawable.wallhaven_72rxqo,
            painterDescription = "Still Life of Blue Rose and Other Flowers"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArtDescriptorPreview() {
    ComposeArtSpaceTheme {
        ArtDescriptor(
            name = "Still Life of Blue Rose and Other Flowers",
            artist = "Owen Scott",
            year = 2021,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayControllerPreview() {
    ComposeArtSpaceTheme {
        DisplayController(
            onPreviousClick = {},
            onNextClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayControllerButtonPreview() {
    ComposeArtSpaceTheme {
        DisplayControllerButton(textId = R.string.previous_button_text,
            onClick = {})
    }
}