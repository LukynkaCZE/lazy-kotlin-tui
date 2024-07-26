package cz.lukynka.components

import cz.lukynka.AutoSizeAxis
import cz.lukynka.BindableList
import cz.lukynka.Drawable
import cz.lukynka.util.Vector2

class DrawableTextListContainer : Drawable() {

    var list: BindableList<String> = BindableList()
    var autoSizeAxis: AutoSizeAxis? = null

    override fun drawContent() {
        var longestLine = 0
        list.values.forEach {
            if(it.length > longestLine) longestLine = it.length
        }
        if(autoSizeAxis != null) {
            size = when(autoSizeAxis!!) {
                AutoSizeAxis.BOTH -> Vector2(longestLine +2, list.size + 1)
                AutoSizeAxis.Y -> Vector2(size.x, list.size + 1)
                AutoSizeAxis.X -> Vector2(longestLine +2, size.y)
            }
        }
        list.values.forEachIndexed { index: Int, s: String ->
            s.forEachIndexed { charIndex: Int, char ->
                buffer[index][charIndex +1] = char
            }
        }
    }
}