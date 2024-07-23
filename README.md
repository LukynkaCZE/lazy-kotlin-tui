# lazy-kotlin-tui

A kotlin terminal ui library inspired by [lazygit](https://github.com/jesseduffield/lazygit) and [osu!framework](https://github.com/ppy/osu-framework/)

This library is work in progress!!!!

```kotlin
val screen = screen {
    size = Vector2(terminal.width, terminal.height)
    hasBorder = true
    children = mutableListOf(
        drawableText {
            size = Vector2(20, 5)
            position = Vector2(5 ,5)
            text = "hello there!"
            hasBorder = true
        },
        drawableRectangle {
            size = Vector2(30, 10)
            position = Vector2(30, 20)
            hasBorder = true
            filling = '#'
        }
    )
}

screen.render()
```

![image](https://github.com/user-attachments/assets/ac483a78-ff0a-495c-8c8f-ad86ad5239c8)

