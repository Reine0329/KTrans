package org.sakura.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.sakura.project.ui.screen.AppScreen

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KTrans - Translation Tool"
    ) {
        AppScreen()
    }
}