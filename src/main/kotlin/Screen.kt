package cz.lukynka

class Screen() : Drawable() {

    override fun drawContent() {} // screen doesn't have its own drawing stuff

    fun render() {
        val screenBuffer = Array(size.y) { CharArray(size.x) { ' ' } }

        draw(screenBuffer)

        for (line in screenBuffer) {
            Instance.terminal.writer().print( "\n" +line.concatToString())
        }
    }
}