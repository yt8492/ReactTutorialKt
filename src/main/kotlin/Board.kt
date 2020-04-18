import react.*
import react.dom.div

class Board(
    props: RProps
) : RComponent<RProps, Board.State>(props) {

    init {
        state.apply {
            squares = List(9) {
                null
            }
            xIsNext = true
        }
    }

    private fun handleClick(i: Int) {
        if (calculateWinner() != null && state.squares[i] != null) {
            return
        }
        val newSquares = state.squares.toMutableList()
        newSquares[i] = if (state.xIsNext) "X" else "O"
        setState {
            squares = newSquares
            xIsNext = !state.xIsNext
        }
    }

    private fun calculateWinner(): String? {
        val lines = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )
        val squares = state.squares
        return lines.firstOrNull {
            val (a, b, c) = it
            squares[a] != null &&
                    squares[a] == squares[b] &&
                    squares[a] == squares[c]
        }?.let {
            squares[it[0]]
        }
    }

    private fun RBuilder.renderSquare(i: Int) {
        square(state.squares[i]) {
            handleClick(i)
        }
    }

    override fun RBuilder.render() {
        val winner = calculateWinner()
        val status = if (winner != null) {
            "Winner: $winner"
        } else {
            "Next player: ${if (state.xIsNext) "X" else "O"}"
        }
        div {
            div(classes = "status") {
                + status
            }
            div(classes = "board-row") {
                renderSquare(0)
                renderSquare(1)
                renderSquare(2)
            }
            div(classes = "board-row") {
                renderSquare(3)
                renderSquare(4)
                renderSquare(5)
            }
            div(classes = "board-row") {
                renderSquare(6)
                renderSquare(7)
                renderSquare(8)
            }
        }
    }

    interface State : RState {
        var squares: List<String?>
        var xIsNext: Boolean
    }
}