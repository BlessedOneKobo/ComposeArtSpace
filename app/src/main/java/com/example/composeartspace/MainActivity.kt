package com.example.composeartspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
                }
            }
        }
    }
}

@Composable
fun DisplayControllerButton(
    @StringRes textId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
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
        DisplayControllerButton(
            textId = R.string.previous_button_text,
            onClick = {})
        DisplayControllerButton(
            textId = R.string.next_button_text,
            onClick = {})
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
        DisplayControllerButton(
            textId = R.string.previous_button_text,
            onClick = {})
    }
}