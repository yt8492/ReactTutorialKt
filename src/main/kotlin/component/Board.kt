package component

import model.GameState
import react.*
import react.dom.div

fun RBuilder.board(
    state: GameState,
    onClick: (i: Int) -> Unit
) {
    (0..2).forEach { i ->
        div(classes = "board-row") {
            (0..2).forEach { j ->
                val index = i * 3 + j
                square(state.board[index]) {
                    onClick(index)
                }
            }
        }
    }
}