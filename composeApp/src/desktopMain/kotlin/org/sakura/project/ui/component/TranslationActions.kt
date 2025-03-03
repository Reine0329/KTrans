package org.sakura.project.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sakura.project.ui.viewmodel.KTransViewModel

@Composable
fun TranslationActions(viewModel: KTransViewModel) {
    val isLoading = viewModel.uiState.value.isLoading
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = viewModel::submitTranslation,
            enabled = viewModel.uiState.value.query.isNotBlank() && !isLoading,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text(if (isLoading) "Loading..." else "Translate")
        }
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }
    }
}