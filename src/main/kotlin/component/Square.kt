package component

import kotlinx.html.js.onClickFunction
import model.Player
import org.w3c.dom.events.Event
import react.*
import react.dom.button

fun RBuilder.square(player: Player?, onClick: (Event) -> Unit) {
    button(classes = "square") {
        + (player?.toString() ?: "")
        attrs.onClickFunction = onClick
    }
}
