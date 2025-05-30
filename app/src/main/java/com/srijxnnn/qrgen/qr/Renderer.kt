package com.srijxnnn.qrgen.qr

import android.graphics.Bitmap
import android.graphics.Color

object Renderer {
    fun renderMatrixToBitmap(matrix: Array<IntArray>, pixelSize: Int): Bitmap {
        val size = matrix.size * pixelSize
        val pixels = IntArray(size * size)
        for (y in matrix.indices) {
            for (x in matrix[y].indices) {
                val color = if (matrix[y][x] == 1) {
                    Color.BLACK
                } else {
                    Color.WHITE
                }
                for (dy in 0 until pixelSize) {
                    val rowStart = (y * pixelSize + dy) * size + x * pixelSize
                    for (dx in 0 until pixelSize) {
                        pixels[rowStart + dx] = color
                    }
                }
            }
        }

        return Bitmap.createBitmap(pixels, size, size, Bitmap.Config.ARGB_8888)
    }

}