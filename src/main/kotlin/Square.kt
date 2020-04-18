import kotlinx.html.js.onClickFunction
import react.*
import react.dom.button

class Square(
    props: Props
) : RComponent<Square.Props, Square.State>(props) {
    override fun RBuilder.render() {
        button(classes = "square") {
            + (state.value ?: "")
            attrs.onClickFunction = {
                setState {
                    value = "X"
                }
            }
        }
    }

    interface Props : RProps {
        var value: Int
    }

    interface State : RState {
        var value: String?
    }
}