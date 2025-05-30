package com.srijxnnn.qrgen.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Generate: Screen("generate", "Generate", Icons.Filled.Create)
    object Scan: Screen("scan", "Scan", Icons.Filled.QrCodeScanner)
}

val bottomNavItems = listOf(
    Screen.Generate,
    Screen.Scan
)