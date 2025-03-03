package org.sakura.project.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sakura.project.logic.model.KTransRequest
import org.sakura.project.logic.model.KTransResult
import org.sakura.project.logic.network.AppNetwork
import org.sakura.project.logic.util.Language

class KTransViewModel : ViewModel() {

    data class UIState(
        val query: String = "",
        val selectedSource: Language = Language.ENGLISH,
        val selectedTarget: Language = Language.CHINESE,
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val translationResult: KTransResult? = null
    )

    var uiState = mutableStateOf(UIState())
        private set

    fun updateQuery(query: String) {
        uiState.value = uiState.value.copy(query = query)
    }

    fun updateSourceLanguage(language: Language) {
        uiState.value = uiState.value.copy(selectedSource = language)
    }

    fun updateTargetLanguage(language: Language) {
        uiState.value = uiState.value.copy(selectedTarget = language)
    }

    fun switchLanguages() {
        val current = uiState.value
        uiState.value = current.copy(
            selectedSource = current.selectedTarget,
            selectedTarget = current.selectedSource
        )
    }

    fun submitTranslation() {
        val query = uiState.value.query
        if (query.isBlank()) return

        uiState.value = uiState.value.copy(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            try {
                val accessToken = AppNetwork.getAccessToken(
                    grantType = "client_credentials",
                    clientId = "your_app_id_here",
                    clientSecret = "your_secret_key_here"
                )
                val result = AppNetwork.translate(
                    request = KTransRequest(
                        from = uiState.value.selectedSource.code,
                        to = uiState.value.selectedTarget.code,
                        q = query
                    ),
                    accessToken = accessToken.accessToken
                )
                uiState.value = uiState.value.copy(
                    translationResult = result,
                    isLoading = false
                )
            } catch (e: Exception) {
                uiState.value = uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message
                )
            }
        }
    }
}