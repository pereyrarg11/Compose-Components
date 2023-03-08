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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pereyrarg11.composecomponents.R
import com.pereyrarg11.composecomponents.ui.theme.ComposeComponentsTheme
import com.pereyrarg11.composecomponents.ui.views.actions.Actions
import com.pereyrarg11.composecomponents.ui.views.customer.CustomerFormGroup
import com.pereyrarg11.composecomponents.ui.views.dialogs.DiscardDialog
import com.pereyrarg11.composecomponents.ui.views.food.FoodFormGroup
import com.pereyrarg11.composecomponents.ui.views.food.buildFoodOptions
import com.pereyrarg11.composecomponents.ui.views.payment.PaymentMethodFormGroup
import com.pereyrarg11.composecomponents.ui.views.slices.SlicesFormGroup
import com.pereyrarg11.composecomponents.ui.views.species.ShoreFormGroup
import com.pereyrarg11.composecomponents.ui.views.species.buildShoreOptions

/**
 * Components documentation: https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary
 */
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
        var customerName by rememberSaveable { mutableStateOf("") }

        /* food */
        var foodOptionsSelected: List<String> by rememberSaveable {
            mutableStateOf(emptyList())
        }
        val foodOptions = buildFoodOptions(foodOptionsSelected) { optionName, isChecked ->
            foodOptionsSelected = if (isChecked) {
                val mutable = foodOptionsSelected.toMutableList()
                mutable.add(optionName)
                mutable
            } else {
                val mutable = foodOptionsSelected.toMutableList()
                mutable.removeAll { it == optionName }
                mutable
            }
        }

        var shoreSelected by rememberSaveable { mutableStateOf("") }
        val shoreOptions = buildShoreOptions(shoreSelected) { newSelection ->
            shoreSelected = newSelection
        }

        var paymentMethod by rememberSaveable { mutableStateOf("") }

        var sliceCount by rememberSaveable { mutableStateOf(4) }
        var sliceSliderValue by rememberSaveable { mutableStateOf(4f) }

        var showDiscardDialog by rememberSaveable { mutableStateOf(false) }

        val submitEnabled = customerName.isNotEmpty()
                && foodOptionsSelected.isNotEmpty()
                && shoreSelected.isNotEmpty()
                && paymentMethod.isNotEmpty()

        val discardEnabled = customerName.isNotEmpty()
                || foodOptionsSelected.isNotEmpty()
                || shoreSelected.isNotEmpty()
                || paymentMethod.isNotEmpty()

        FormHeader()
        Spacer(
            Modifier
                .height(32.dp)
                .fillMaxWidth()
        )
        Column(Modifier.width(300.dp)) {
            CustomerFormGroup(name = customerName) { customerName = it }
            FoodFormGroup(options = foodOptions)
            ShoreFormGroup(options = shoreOptions)
            PaymentMethodFormGroup(selectedMethod = paymentMethod) {
                paymentMethod = it
            }
            SlicesFormGroup(
                sliceCount = sliceCount,
                sliderValue = sliceSliderValue,
                onSliderChange = { sliceSliderValue = it }
            ) { sliceCount = sliceSliderValue.toInt() }
            Actions(
                actionsEnabled = submitEnabled,
                discardEnabled = discardEnabled,
                onSubmitListener = { Log.i("Pets", "Guardando información") },
                onDiscardListener = { showDiscardDialog = true },
            )
            DiscardDialog(
                isDisplayed = showDiscardDialog,
                onDismissListener = { showDiscardDialog = false }
            ) {
                customerName = ""
                foodOptionsSelected = emptyList()
                shoreSelected = ""
                paymentMethod = ""
                sliceCount = 4
                sliceSliderValue = 4f
                showDiscardDialog = false
            }
        }
    }
}

@Composable
fun FormHeader() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(id = R.string.app_name),
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