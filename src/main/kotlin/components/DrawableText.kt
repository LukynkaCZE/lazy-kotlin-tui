package cz.lukynka.components

import cz.lukynka.Drawable

class DrawableText() : Drawable() {

    var text: String = ""

    override fun drawContent() {
        val textLines = text.chunked(size.x - 2)

        for ((i, line) in textLines.withIndex()) {
            if (i < size.y - 2) {
                for ((j, char) in line.withIndex()) {
                    buffer[i + 1][j + 1] = char
                }
            }
        }
    }
}