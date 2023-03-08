package com.pereyrarg11.composecomponents.ui.views.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.pereyrarg11.composecomponents.ui.views.components.radiobutton.RadioButtonOption
import com.pereyrarg11.composecomponents.ui.views.components.radiobutton.ShoreRadioButton
import com.pereyrarg11.composecomponents.ui.views.size.SizeCatalog

@Composable
fun SizeDialog(
    isDisplayed: Boolean,
    draftSelected: String,
    onDraftSelect: (String) -> Unit,
    onDismissListener: () -> Unit,
    onConfirmListener: () -> Unit
) {
    if (isDisplayed) {
        Dialog(onDismissRequest = onDismissListener) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                // Title
                Text(
                    text = "Selecciona un tamaño",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    fontWeight = FontWeight.SemiBold
                )

                Divider(modifier = Modifier.fillMaxWidth(), color = Color.LightGray)

                // Options
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    SizeCatalog.values().forEach { sizeOption ->
                        ShoreRadioButton(
                            optionConfig = RadioButtonOption(
                                label = sizeOption.description,
                                isSelected = sizeOption.description.equals(draftSelected, true),
                                onClickListener = { onDraftSelect(sizeOption.description) })
                        )
                    }
                }

                Divider(modifier = Modifier.fillMaxWidth(), color = Color.LightGray)

                // Actions
                Row(
                    Modifier
                        .align(Alignment.End)
                        .padding(horizontal = 16.dp)
                ) {
                    TextButton(onClick = onDismissListener) {
                        Text(text = "Cancelar")
                    }
                    TextButton(
                        onClick = onConfirmListener,
                        enabled = draftSelected.isNotEmpty()
                    ) {
                        Text(text = "Aceptar")
                    }
                }
            }
        }
    }
}
