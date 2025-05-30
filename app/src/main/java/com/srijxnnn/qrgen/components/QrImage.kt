package com.srijxnnn.qrgen.components

import android.graphics.Bitmap
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QrImage(bitmap: Bitmap, isLoading: Boolean, modifier: Modifier = Modifier) {
    Card {
        Box {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "QR code",
                modifier = modifier.graphicsLayer(
                    renderEffect = if (isLoading && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) RenderEffect.createBlurEffect(
                        5f,
                        5f,
                        Shader.TileMode.MIRROR
                    ).asComposeRenderEffect() else null
                )
            )
        }

    }
    Text(
        "${bitmap.width} x ${bitmap.height}",
        fontSize = 12.sp,
        modifier = Modifier.padding(12.dp)
    )
}