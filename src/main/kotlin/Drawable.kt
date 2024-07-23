package cz.lukynka

import cz.lukynka.util.Vector2

abstract class Drawable {

    var position: Vector2 = Vector2(0, 0)
    var size: Vector2 = Vector2(0, 0)

    var hasBorder: Boolean = false

    //TODO Inherit size from parent
    open var autoSizeAxis: AutoSizeAxis = AutoSizeAxis.NONE

    var children: MutableList<Drawable> = mutableListOf()

    lateinit var buffer: Array<CharArray>

    abstract fun drawContent()

    fun draw(parentBuffer: Array<CharArray>) {
        buffer = Array(size.y) { CharArray(size.x) { ' ' } }

        drawContent()

        if (hasBorder) {
            drawBorder()
        }

        mergeBuffer(parentBuffer)

        for (child in children) {
            child.draw(parentBuffer)
        }
    }

    fun addChild(child: Drawable) {
        children.add(child)
    }

    fun removeChild(child: Drawable) {
        children.remove(child)
    }

    fun resize(newWidth: Int, newHeight: Int) {
        size = Vector2(newWidth, newHeight)
    }

    fun resize(vector2: Vector2) {
        size = vector2
    }

    //╭╮╰╯│─"
    private fun drawBorder() {

        val topLeft = '╭'
        val topRight = '╮'
        val bottomLeft = '╰'
        val bottomRight = '╯'
        val vertical = '│'
        val horizontal = '─'
        val title = this::class.simpleName ?: ""

        // top line
        buffer[0][0] = topLeft
        val titleStart = 1
        val titleEnd = minOf(titleStart + title.length, size.x - 1)

        // title
        if (title.isNotEmpty()) {
            for (i in titleStart until titleEnd) {
                buffer[0][i] = title[i - titleStart]
            }
        }

        // after title line
        for (i in titleEnd until size.x - 1) {
            buffer[0][i] = horizontal
        }

        buffer[0][size.x - 1] = topRight

        // bottom border
        buffer[size.y - 1][0] = bottomLeft
        buffer[size.y - 1][size.x - 1] = bottomRight
        for (i in 1 until size.x - 1) {
            buffer[size.y - 1][i] = horizontal
        }

        // left and right
        for (i in 1 until size.y - 1) {
            buffer[i][0] = vertical
            buffer[i][size.x - 1] = vertical
        }
    }

    private fun mergeBuffer(parentBuffer: Array<CharArray>) {
        for (i in buffer.indices) {
            for (j in buffer[i].indices) {
                val parentY = position.y + i
                val parentX = position.x + j
                if (parentY in parentBuffer.indices && parentX in parentBuffer[parentY].indices) {
                    if (parentBuffer[parentY][parentX] == ' ') {
                        parentBuffer[parentY][parentX] = buffer[i][j]
                    }
                }
            }
        }
    }
}

enum class AutoSizeAxis {
    X,
    Y,
    BOTH,
    NONE
}