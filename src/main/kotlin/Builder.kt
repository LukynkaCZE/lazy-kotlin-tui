package cz.lukynka

import cz.lukynka.components.DrawableRectangle
import cz.lukynka.components.DrawableText

fun screen(init: Screen.() -> Unit): Screen {
    return Screen().apply(init)
}

fun drawableText(init: DrawableText.() -> Unit): DrawableText {
    return DrawableText().apply(init)
}

fun drawableRectangle(init: DrawableRectangle.() -> Unit): DrawableRectangle {
    return DrawableRectangle().apply(init)
}