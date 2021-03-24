package com.hwanyu365

import javafx.scene.text.FontWeight
import tornadofx.*

class HelloApp : App(HelloView::class, Styles::class)

class HelloView : View() {
    override val root = hbox {
        label("Hello, Kotlin")
    }
}

class Styles : Stylesheet() {
    init {
        label {
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
            backgroundColor += c("#cecece")
        }
    }
}