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
        div(classes = "board-row") {
            (0..2).forEach { i ->
                square(state.board[i]) {
                    onClick(i)
                }
            }
        }
        div(classes = "board-row") {
            (3..5).forEach { i ->
                square(state.board[i]) {
                    onClick(i)
                }
            }
        }
        div(classes = "board-row") {
            (6..8).forEach { i ->
                square(state.board[i]) {
                    onClick(i)
                }
            }
        }
    }
}