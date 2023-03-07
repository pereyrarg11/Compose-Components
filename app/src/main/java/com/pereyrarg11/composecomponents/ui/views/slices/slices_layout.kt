package com.pereyrarg11.composecomponents.ui.views.slices

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.pereyrarg11.composecomponents.ui.views.form.group.FormGroupLayout

@Composable
fun SlicesFormGroup(
    sliceCount: Int,
    sliderValue: Float,
    onSliderChange: (Float) -> Unit,
    onSliceChange: () -> Unit
) {
    FormGroupLayout(label = "Rebanadas") {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = sliceCount.toString())
            Slider(
                // https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#Slider(kotlin.Float,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Boolean,kotlin.ranges.ClosedFloatingPointRange,kotlin.Int,kotlin.Function0,androidx.compose.foundation.interaction.MutableInteractionSource,androidx.compose.material.SliderColors)
                value = sliderValue,
                valueRange = 4f..16f,
                steps = 2,
                onValueChange = onSliderChange,
                onValueChangeFinished = onSliceChange
            )
        }
    }
}

@Preview
@Composable
fun SlicesPreview() {
    var sliceCount by rememberSaveable {
        mutableStateOf(4)
    }
    var sliderValue by rememberSaveable {
        mutableStateOf(4f)
    }
    SlicesFormGroup(
        sliceCount = sliceCount,
        sliderValue = sliderValue,
        onSliderChange = { sliderValue = it }
    ) { sliceCount = sliderValue.toInt() }
}