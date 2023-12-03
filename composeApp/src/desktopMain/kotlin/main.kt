import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import rockets.DatabaseDriverFactory

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App()
}