package component

import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.button

fun RBuilder.square(value: String?, onClick: (Event) -> Unit) {
    button(classes = "square") {
        + (value ?: "")
        attrs.onClickFunction = onClick
    }
}
