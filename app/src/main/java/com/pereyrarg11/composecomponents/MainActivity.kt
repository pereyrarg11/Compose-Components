package com.pereyrarg11.composecomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pereyrarg11.composecomponents.ui.theme.ComposeComponentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeComponentsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //MyText()
                    /*var myText by remember {
                        mutableStateOf("")
                    }
                    Column {
                        MyTextField(myText) {
                            myText = if (it.contains("A")) {
                                it.replace("A", "")
                            } else {
                                it
                            }
                        }
                    }*/
                    //MyButtons()
                    //MyImage()
                    //MyIcon()
                    MyProgress()
                }
            }
        }
    }
}

@Composable
fun MyText() {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Esto es un ejemplo")
        Text(text = "Esto es un ejemplo", color = Color.Blue)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.ExtraBold)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.Light)
        Text(text = "Esto es un ejemplo", style = TextStyle(fontFamily = FontFamily.Cursive))
        // change text decoration through style attribute
        Text(
            text = "Esto es un ejemplo",
            style = TextStyle(textDecoration = TextDecoration.LineThrough)
        )
        Text(
            text = "Esto es un ejemplo",
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
        Text(
            text = "Esto es un ejemplo", style = TextStyle(
                textDecoration = TextDecoration.combine(
                    listOf(TextDecoration.LineThrough, TextDecoration.Underline)
                )
            )
        )
        // change text decoration with the specific attribute
        Text(
            text = "Esto es un ejemplo",
            textDecoration = TextDecoration.LineThrough
        )
        Text(
            text = "Esto es un ejemplo",
            textDecoration = TextDecoration.Underline
        )
        Text(
            text = "Esto es un ejemplo",
            textDecoration = TextDecoration.combine(
                listOf(TextDecoration.LineThrough, TextDecoration.Underline)
            )
        )
        Text(text = "Esto es un ejemplo", fontSize = 30.sp)
    }
}

@Composable
fun MyTextField(myText: String, onValueChanged: (String) -> Unit) {
    // or you can use TextField
    OutlinedTextField(
        value = myText,
        onValueChange = {
            onValueChanged(it)
        },
        label = { Text(text = "Introduce tu nombre") },
        modifier = Modifier.padding(24.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Magenta,
            unfocusedBorderColor = Color.Yellow,
        )
    )
}

@Composable
fun MyButtons() {
    var isEnabled by rememberSaveable {
        mutableStateOf(true)
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            onClick = { isEnabled = false },
            enabled = isEnabled,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Yellow,
                contentColor = Color.Black,
                disabledBackgroundColor = Color.Gray,
                disabledContentColor = Color.DarkGray,
            ),
            border = BorderStroke(1.dp, Color.Red),
        ) {
            Text(text = "Button")
        }

        OutlinedButton(
            onClick = { isEnabled = false },
            enabled = isEnabled,
        ) {
            Text(text = "OutlinedButton")
        }

        TextButton(
            onClick = { isEnabled = false },
            enabled = isEnabled,
        ) {
            Text(text = "TextButton")
        }
    }
}

@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Image",
        alpha = 0.75f,
        //modifier = Modifier.clip(RoundedCornerShape(25f))
        modifier = Modifier
            .clip(CircleShape)
            .border(3.dp, Color.Red, CircleShape)
    )
}

@Composable
fun MyIcon() {
    //more icons: https://fonts.google.com/icons
    Icon(imageVector = Icons.Filled.StarBorder, "Star", tint = Color.Red)
}

@Composable
fun MyProgress() {
    var progressStatus by rememberSaveable {
        mutableStateOf(50)
    }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = progressStatus.toString())
        CircularProgressIndicator(progress = progressStatus/100f)
        Row(horizontalArrangement = Arrangement.Center) {
            TextButton(
                onClick = { if (progressStatus < 100) progressStatus += 10 },
                enabled = progressStatus < 100
            ) {
                Text(text = "Incrementar")
            }
            TextButton(
                onClick = { if (progressStatus > 0) progressStatus -= 10 },
                enabled = progressStatus > 0
            ) {
                Text(text = "Reducir")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeComponentsTheme {
        //MyText()
        //MyTextField()
        //MyButtons()
        //MyImage()
        //MyIcon()
        MyProgress()
    }
}