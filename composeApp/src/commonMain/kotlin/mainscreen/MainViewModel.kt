package mainscreen

import ViewEvent
import ViewModel
import getDatabaseDriverFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mainscreen.contract.MainEvent
import mainscreen.contract.MainState
import rockets.DatabaseDriverFactory
import rockets.api.SpaceXSDK

class MainViewModel : ViewModel<MainState, MainEvent>(initialState = MainState.Empty) {

    override fun obtainEvent(newEvent: MainEvent) {
        when (val state = viewState) {
            is MainState.Empty -> catchEvent(newEvent, state)
            is MainState.ShowRockets -> catchEvent(newEvent, state)
            is MainState.Loading -> catchEvent(newEvent, state)
        }
    }

    private fun catchEvent(event: MainEvent, state: MainState.Empty) {
        when (event) {
            MainEvent.ClickLoad -> {
                performMakeCall(false)
            }
        }
    }

    private fun catchEvent(event: MainEvent, state: MainState.ShowRockets) {
        when(event) {
            MainEvent.ClickLoad -> {
                performMakeCall(true)
            }
        }
    }

    private fun catchEvent(event: MainEvent, state: MainState.Loading) {

    }

    private fun performMakeCall(forceReload : Boolean){
        val sdk = SpaceXSDK(getDatabaseDriverFactory())
        val scope = CoroutineScope(Dispatchers.Default)
        viewState = MainState.Loading
        scope.launch {
            kotlin.runCatching {
                sdk.getLaunches(forceReload = forceReload)
            }.onSuccess {
                viewState = MainState.ShowRockets(it)
            }.onFailure {
                viewState = MainState.Empty
            }
        }
    }
}