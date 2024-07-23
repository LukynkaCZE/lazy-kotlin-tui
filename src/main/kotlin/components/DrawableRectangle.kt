package cz.lukynka.components

import cz.lukynka.Drawable

class DrawableRectangle: Drawable() {

    var filling: Char = '#'

    override fun drawContent() {
        for (i in 0 until size.y) {
            for (j in 0 until size.x) {
                buffer[i][j] = filling
            }
        }
    }
}