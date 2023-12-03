import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ViewEvent
interface ViewState

abstract class ViewModel<UiState: ViewState, Event : ViewEvent>(private val initialState: UiState) {

    abstract fun obtainEvent(newEvent: Event)

    private val state: MutableState<UiState> = mutableStateOf(initialState)

    var viewState : UiState
        get() = state.value
        set(value) {state.value = value}
}