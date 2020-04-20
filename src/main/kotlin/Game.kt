import react.*
import react.dom.div
import react.dom.ol

class Game : RComponent<RProps, Game.State>() {
    init {
        state.apply {
            val board = List(9) {
                null
            }
            gameState = GameState.Playing(Player.X, board)
        }
    }

    override fun RBuilder.render() {
        div(classes = "game") {
            div(classes = "game-board") {
                board(state.gameState) { i ->
                    if (state.gameState.board[i] != null) {
                        return@board
                    }
                    val playing = state.gameState as? GameState.Playing
                    if (playing != null) {
                        setState {
                            gameState = playing.calculateNextState(i)
                        }
                    }
                }
            }
            div(classes = "game-info") {
                div {}
                ol {}
            }
        }
    }

    interface State : RState {
        var gameState: GameState
    }
}