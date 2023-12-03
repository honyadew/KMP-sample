import rockets.DatabaseDriverFactory

actual fun getDatabaseDriverFactory(): DatabaseDriverFactory {
    return DatabaseDriverFactory()
}