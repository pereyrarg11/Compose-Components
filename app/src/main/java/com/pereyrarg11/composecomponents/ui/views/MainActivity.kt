package com.pereyrarg11.composecomponents.ui.views

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
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
import com.pereyrarg11.composecomponents.R
import com.pereyrarg11.composecomponents.ui.theme.ComposeComponentsTheme
import com.pereyrarg11.composecomponents.ui.views.checkbox.FoodFormGroup
import com.pereyrarg11.composecomponents.ui.views.checkbox.createFoodOptions

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
                    PetForm()
                }
            }
        }
    }
}

@Composable
fun PetForm() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var name by rememberSaveable { mutableStateOf("") }
        val foodOptions = createFoodOptions(listOf("Carne", "Croquetas", "Presas"))

        val ctaEnabled = name.isNotEmpty()
                && foodOptions.any { it.isChecked }

        val onNameChanged: (String) -> Unit = { name = it }
        val onDiscardClickListener: () -> Unit = {
            Log.d("Pets", "Limpiando formulario")
            name = ""
        }
        val onSubmitClickListener: () -> Unit = {
            Log.i("Pets", "Guardando información")
        }

        FormHeader()
        Spacer(
            Modifier
                .height(32.dp)
                .fillMaxWidth()
        )
        Column(Modifier.width(300.dp)) {
            InfoFormGroup(name = name, onNameChanged = onNameChanged)
            FoodFormGroup(options = foodOptions)
            Actions(
                actionsEnabled = ctaEnabled,
                onSubmitListener = onSubmitClickListener,
                onDiscardListener = onDiscardClickListener
            )
        }
    }
}

@Composable
fun FormHeader() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(
            text = "Mis mascotas",
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp
        )
    }
}

@Composable
fun FormGroupLabel(text: String) {
    Text(text = text, textDecoration = TextDecoration.Underline, fontWeight = FontWeight.Bold)
}

@Composable
fun TextInputForm(value: String, label: String, onValueChanged: (String) -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChanged,
            label = { Text(text = label) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun InfoFormGroup(name: String, onNameChanged: (String) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
    ) {
        FormGroupLabel(text = "Información básica")
        Spacer(
            Modifier
                .height(8.dp)
                .fillMaxWidth()
        )
        TextInputForm(value = name, label = "Nombre", onValueChanged = onNameChanged)
    }
}

@Composable
fun Actions(actionsEnabled: Boolean, onSubmitListener: () -> Unit, onDiscardListener: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
        Row {
            OutlinedButton(onClick = onDiscardListener, enabled = actionsEnabled) {
                Text(text = "Descartar")
            }
            Spacer(
                modifier = Modifier
                    .width(8.dp)
                    .fillMaxHeight()
            )
            Button(onClick = onSubmitListener, enabled = actionsEnabled) {
                Text(text = "Guardar")
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
        CircularProgressIndicator(progress = progressStatus / 100f)
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
        PetForm()
    }
}