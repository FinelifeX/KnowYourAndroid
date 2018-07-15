package itis.kpfu.ru.knowyourandroid.utils

import android.R.attr
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader.TileMode.CLAMP
import android.support.design.R

/**
 * Created by Ilya Zakharchenko on 10.07.2018.
 */

class ImageHelper {

    companion object {

        fun roundIcon(bitmap: Bitmap) {
            val shader: BitmapShader = BitmapShader(bitmap, CLAMP, CLAMP)
            val canvas = Canvas()

            val paint = Paint()
            paint.setAntiAlias(true)
            paint.setShader(shader)

            val rect = RectF(0.0f, 0.0f, attr.width.toFloat(), R.attr.height.toFloat())

// rect contains the bounds of the shape
// radius is the radius in pixels of the rounded corners
// paint contains the shader that will texture the shape
            canvas.drawRoundRect(rect, attr.radius.toFloat(), attr.radius.toFloat(), paint)
        }
    }
}