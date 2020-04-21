package component

import model.GameState
import react.*
import react.dom.div

fun RBuilder.board(
    state: GameState,
    onClick: (i: Int) -> Unit
) {
    val status = when (state) {
        is GameState.Playing -> {
            "Next Player: ${state.turn}"
        }
        is GameState.End -> {
            val winner = state.winner
            if (winner == null) {
                "Draw"
            } else {
                "Winner: $winner"
            }
        }
    }
    div {
        div(classes = "status") {
            + status
        }
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
}