import com.honya.sss.context
import rockets.DatabaseDriverFactory

actual fun getDatabaseDriverFactory(): DatabaseDriverFactory {
    return DatabaseDriverFactory(context)
}