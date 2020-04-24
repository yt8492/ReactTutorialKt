package component

import kotlinx.html.js.onClickFunction
import model.GameState
import model.Player
import react.*
import react.dom.*

class Game : RComponent<RProps, Game.State>() {
    init {
        state.apply {
            val board = List(9) {
                null
            }
            gameStateHistory = listOf(GameState.Playing(Player.X, board))
            currentStep = gameStateHistory.lastIndex
        }
    }

    override fun RBuilder.render() {
        val history = state.gameStateHistory.slice(0..state.currentStep)
        val gameState = history.last()
        val statusText = when (gameState) {
            is GameState.Playing -> {
                "Next Player: ${gameState.turn}"
            }
            is GameState.End -> {
                val winner = gameState.winner
                if (winner == null) {
                    "Draw"
                } else {
                    "Winner: $winner"
                }
            }
        }
        div(classes = "game") {
            div(classes = "game-board") {
                board(gameState) { i ->
                    if (gameState.board[i] != null) {
                        return@board
                    }
                    val playing = gameState as? GameState.Playing
                    if (playing != null) {
                        setState {
                            gameStateHistory = history + playing.calculateNextState(i)
                            currentStep = gameStateHistory.lastIndex
                        }
                    }
                }
            }
            div(classes = "game-info") {
                div {
                    + statusText
                }
                ol {
                    state.gameStateHistory.forEachIndexed { i, _ ->
                        li {
                            key = "$i"
                            button {
                                + "Go to move #$i"
                                attrs.onClickFunction = {
                                    setState {
                                        currentStep = i
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    interface State : RState {
        var gameStateHistory: List<GameState>
        var currentStep: Int
    }
}