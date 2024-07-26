package cz.lukynka

import cz.lukynka.components.DrawableRectangle
import cz.lukynka.components.DrawableText
import cz.lukynka.components.DrawableTextListContainer

fun screen(init: Screen.() -> Unit): Screen {
    return Screen().apply(init)
}

fun drawableTextListContainer(init: DrawableTextListContainer.() -> Unit): DrawableTextListContainer {
    return DrawableTextListContainer().apply(init)
}

fun drawableText(init: DrawableText.() -> Unit): DrawableText {
    return DrawableText().apply(init)
}

fun drawableRectangle(init: DrawableRectangle.() -> Unit): DrawableRectangle {
    return DrawableRectangle().apply(init)
}