package org.sakura.project.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import org.sakura.project.logic.util.Language
import org.sakura.project.ui.viewmodel.KTransViewModel

@Composable
fun LanguageSelector(viewModel: KTransViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LanguageDropdown(
                label = "Source",
                selectedLanguage = viewModel.uiState.value.selectedSource,
                onLanguageChange = viewModel::updateSourceLanguage,
                modifier = Modifier.weight(1f)
            )
            OutlinedButton(
                onClick = viewModel::switchLanguages,
                modifier = Modifier.weight(1f)
            ) {
                Text("<->")
            }
            LanguageDropdown(
                label = "Target",
                selectedLanguage = viewModel.uiState.value.selectedTarget,
                onLanguageChange = viewModel::updateTargetLanguage,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun LanguageDropdown(
    label: String,
    selectedLanguage: Language,
    onLanguageChange: (Language) -> Unit,
    modifier: Modifier = Modifier
) {
    val expanded = remember { mutableStateOf(false) }
    val buttonWidth = remember { mutableStateOf(0) }

    Column(modifier = modifier) {
        OutlinedButton(
            onClick = { expanded.value = true },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    buttonWidth.value = coordinates.size.width
                }
        ) {
            Text("$label: ${selectedLanguage.displayName}")
        }
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.width(with(LocalDensity.current) { buttonWidth.value.toDp() })
        ) {
            Language.entries.forEach { language ->
                DropdownMenuItem(
                    onClick = {
                        onLanguageChange(language)
                        expanded.value = false
                    }
                ) {
                    Text(language.displayName)
                }
            }
        }
    }
}
