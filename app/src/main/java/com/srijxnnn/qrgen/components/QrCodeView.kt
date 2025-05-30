package com.srijxnnn.qrgen.components

import android.content.res.Configuration
import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.srijxnnn.qrgen.qr.Encoder
import com.srijxnnn.qrgen.qr.Renderer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * Shows a QR code from a text
 */

@Composable
fun QrCodeView(text: String, modifier: Modifier = Modifier) {

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    var bitmap by rememberSaveable { mutableStateOf<Bitmap?>(null) }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var pixelSize by rememberSaveable { mutableFloatStateOf(10f) }
    var debouncedPixelSize by rememberSaveable { mutableIntStateOf(10) }
    val context = LocalContext.current
    val setPixelSize = { value: Float -> pixelSize = value }
    val setDebouncedPixelSize = { value: Int -> debouncedPixelSize = value }


    LaunchedEffect(text, debouncedPixelSize) {
        isLoading = true
        if (text.isNotEmpty()) {
            bitmap = withContext(Dispatchers.Default) {
                val matrix = Encoder.generateQrMatrix(text)
                Renderer.renderMatrixToBitmap(matrix, debouncedPixelSize)
            }
        } else {
            delay(300)
            bitmap = null
        }
        isLoading = false
    }

    AnimatedVisibility(
        visible = text.isNotEmpty(),
        enter = scaleIn() + fadeIn(),
        exit = scaleOut() + fadeOut()
    ) {

        if (isLandscape) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(horizontal = 72.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    bitmap?.let {
                        QrImage(
                            bitmap!!,
                            isLoading,
                            modifier
                        )
                    }
                }

                Spacer(Modifier.width(48.dp))

                bitmap?.let {
                    QrControlsView(
                        context,
                        pixelSize,
                        setPixelSize,
                        setDebouncedPixelSize,
                        bitmap!!,
                        modifier = Modifier.padding(vertical = 24.dp, horizontal = 10.dp)
                    )
                }
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                bitmap?.let {
                    QrImage(
                        bitmap!!,
                        isLoading,
                        modifier
                    )

                    QrControlsView(
                        context,
                        pixelSize,
                        setPixelSize,
                        setDebouncedPixelSize,
                        bitmap!!,
                        modifier = Modifier.padding(vertical = 24.dp, horizontal = 10.dp)
                    )
                }
            }
        }

    }
}