package org.sakura.project.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sakura.project.ui.component.LanguageSelector
import org.sakura.project.ui.component.TranslationActions
import org.sakura.project.ui.component.TranslationInput
import org.sakura.project.ui.component.TranslationResult
import org.sakura.project.ui.viewmodel.KTransViewModel

@Composable
fun AppScreen(viewModel: KTransViewModel = viewModel()) {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(color = Color(0xFFF7F7F7))
                .border(1.dp, Color(0xFFE0E0E0))
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    LanguageSelector(viewModel)
                }
                item {
                    TranslationInput(viewModel)
                }
                item {
                    TranslationActions(viewModel)
                }
                item {
                    TranslationResult(viewModel)
                }
            }
        }
    }
}