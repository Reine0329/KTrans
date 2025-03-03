package org.sakura.project.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sakura.project.ui.viewmodel.KTransViewModel

@Composable
fun TranslationResult(viewModel: KTransViewModel) {
    val result = viewModel.uiState.value.translationResult
    val error = viewModel.uiState.value.errorMessage

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        error?.let {
            Text("Error: $it", color = MaterialTheme.colors.error)
        }
        result?.let {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                elevation = 4.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        "Result (${viewModel.uiState.value.selectedTarget.displayName}):",
                        style = MaterialTheme.typography.subtitle2
                    )

                    val combinedText = result.result.transResult.joinToString("\n") { translation ->
                        translation.dst
                    }

                    SelectionContainer {
                        Text(
                            text = combinedText,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }
}
