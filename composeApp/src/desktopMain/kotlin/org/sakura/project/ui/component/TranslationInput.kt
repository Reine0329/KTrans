package org.sakura.project.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sakura.project.ui.viewmodel.KTransViewModel

@Composable
fun TranslationInput(viewModel: KTransViewModel, maxCharacters: Int = 5000) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 4.dp
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = viewModel.uiState.value.query,
                onValueChange = {
                    if (it.length <= maxCharacters) {
                        viewModel.updateQuery(it)
                    }
                },
                label = { Text("Please enter ${viewModel.uiState.value.selectedSource.displayName}") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Text(
                text = "${viewModel.uiState.value.query.length}/$maxCharacters",
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(0.dp, 0.dp, 24.dp, 16.dp)
            )
        }
    }
}