package com.srijxnnn.qrgen.qr

import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter

object Encoder {
    fun generateQrMatrix(text: String): Array<IntArray> {
        val bitMatrix = MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, 1, 1)
        val matrix = Array (bitMatrix.height) { IntArray(bitMatrix.width) }

        for (y in 0 until bitMatrix.height) {
            for (x in 0 until bitMatrix.width) {
                matrix[y][x] = if (bitMatrix[y, x]) 1 else 0
            }
        }

        return matrix
    }

}