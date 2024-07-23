package cz.lukynka

import cz.lukynka.Instance.terminal
import cz.lukynka.util.Vector2
import kotlin.concurrent.timer

object Instance {
    val terminal = org.jline.terminal.TerminalBuilder.terminal()!!
}

fun main() {

    val text = drawableText {
        size = Vector2(20, 5)
        position = Vector2(5 ,5)
        text.value = "The size is ${terminal.width}x${terminal.height}"
        hasBorder = true
    }

    val screen = screen {
        size = Vector2(terminal.width, terminal.height)
        hasBorder = true
        children = mutableListOf(
            text,
            drawableRectangle {
                size = Vector2(30, 10)
                position = Vector2(30, 20)
                hasBorder = true
                filling = '#'
            }
        )
    }

    screen.render()

    // resize loop TODO move somewhere else
    var prevWidth = 0
    var prevHeight = 0

    val timer = timer(period = 5) {
        if(prevHeight != terminal.height || prevWidth != terminal.width) {
            clearScreen()
            screen.resize(terminal.width, terminal.height)
            text.text.value = "The size is ${terminal.width}x${terminal.height}"
            screen.render()
            prevHeight = terminal.height
            prevWidth = terminal.width
        }
    }
}

fun clearScreen() {
    terminal.writer().println("\u001b[2J\u001b[H")
    terminal.flush()
}