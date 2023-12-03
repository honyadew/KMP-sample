package mainscreen.contract

import ViewEvent

sealed class MainEvent : ViewEvent {
    data object ClickLoad : MainEvent()
}