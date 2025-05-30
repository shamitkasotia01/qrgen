package com.srijxnnn.qrgen.components

import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.srijxnnn.qrgen.R
import com.srijxnnn.qrgen.utils.ImageSaver
import kotlinx.coroutines.delay

@Composable
fun QrControlsView(
    context: Context,
    pixelSize: Float,
    setPixelSize: (Float) -> Unit ,
    setDebouncedPixelSize: (Int) -> Unit,
    bitmap: Bitmap,
    modifier: Modifier = Modifier
    ) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Button(
            onClick = {
                ImageSaver.saveBitmaptoGallery(context, bitmap)
                Toast.makeText(context, "QR code saved to gallery", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth(0.7f),
            contentPadding = PaddingValues(vertical = 12.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                Icon(
                    painter = painterResource(R.drawable.qr_code_24px),
                    contentDescription = null
                )
                Spacer(Modifier.width(24.dp))
                Text(
                    text = "Save QR",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Quality",

                )
            Spacer(Modifier.width(10.dp))
            Slider(
                value = pixelSize,
                onValueChange = setPixelSize,
                valueRange = 1f..50f,
            )
        }


        LaunchedEffect(pixelSize) {
            delay(300)
            setDebouncedPixelSize(pixelSize.toInt())
        }




    }

}