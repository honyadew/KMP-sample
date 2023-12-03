package mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import mainscreen.contract.MainEvent
import mainscreen.contract.MainState

@Composable
fun MainScreen(
    viewModel: MainViewModel = remember { MainViewModel() }
) {
    when(val state = viewModel.viewState){

        MainState.Empty -> {
            Column(
                modifier = Modifier.fillMaxSize().background(color = Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "No Booba, load?")
                Button(onClick = {
                    viewModel.obtainEvent(MainEvent.ClickLoad)
                }){
                    Text(text = "Yes")
                }
            }
        }

        is MainState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        }

        is MainState.ShowRockets -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize().background(color = Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Button(onClick = {viewModel.obtainEvent(MainEvent.ClickLoad)}){
                        Text("Reload")
                    }
                }
                state.rocketLaunch.forEach { rocket ->
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(8.dp).background(Color.Gray)
                        ) {
                            //TODO ofc when click on Links Need to open another screen with full info
                            //TODO date with utc time should show based on local time zone
                            //TODO if something is null no need to show it
                            Text(text = "flight number: ${rocket.flightNumber}", color = Color.Black)
                            Text(text = "name: ${rocket.name}", color = Color.Black, maxLines = 1)
                            Text(text = "date: ${rocket.dataUTC}", color = Color.Black, maxLines = 1)
                            Text(text = "details: ${rocket.details}", color = Color.Black, maxLines = 1)
                            Text(text = "launchSuccess: ${rocket.launchSuccess}", color = Color.Black)
                        }
                    }
                }
            }
        }
    }
}