package com.example.composeartspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun DisplayController(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        DisplayControllerButton(textId = R.string.previous_button_text,
            onClick = {})
        DisplayControllerButton(textId = R.string.next_button_text,
            onClick = {})
    }
}

@Composable
fun ArtDescriptor(
    name: String, artist: String, year: Int, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
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
fun ArtworkWall(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(360.dp),
        shadowElevation = 12.dp,
    ) {
        Image(
            painter = painterResource(id = R.drawable.wallhaven_72rxqo),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.padding(all = 32.dp),
        )
    }
}

@Composable
fun ComposeArtSpaceApp() {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        ArtworkWall(
            modifier = Modifier
                .padding(bottom = 52.dp)
        )
        Column {
            ArtDescriptor(
                name = "Still Life of Blue Rose and Other Flowers",
                artist = "Owen Scott",
                year = 2021,
            )
            Spacer(modifier = Modifier.size(8.dp))
            DisplayController(
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
        ArtworkWall()
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
        DisplayController()
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