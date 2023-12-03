package mainscreen.contract

import ViewState
import rockets.RocketLaunch

sealed class MainState : ViewState {
    data object Empty : MainState()
    data object Loading : MainState()
    data class ShowRockets(val rocketLaunch: List<RocketLaunch>) : MainState()
}