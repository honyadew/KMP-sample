package rockets

import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import com.cache.AppDatabase
import com.cache.Launch
import kotlinx.serialization.descriptors.PrimitiveKind


internal class Database(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = AppDatabase(databaseDriverFactory.createDriver())

    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllLaunches()
        }
    }

    internal fun getAllLaunches() : List<RocketLaunch> {
        return dbQuery.selectAllLaunchesInfo(::mapLaunchSelecting).executeAsList()
    }

    private fun mapLaunchSelecting(
        flightNumber: Long,
        missionName: String,
        details : String?,
        launchSuccess : Boolean?,
        launchDateUTC: String,
        patchUrlSmall: String?,
        patchUrlLarge: String?,
        articleUrl: String?
    ) : RocketLaunch {
        return RocketLaunch(
            flightNumber = flightNumber.toInt(),
            name = missionName,
            dataUTC = launchDateUTC,
            details = details,
            launchSuccess = launchSuccess,
            links = Links(
                patch = Patch(
                    small = patchUrlSmall,
                    large = patchUrlLarge
                ),
                article = articleUrl
            )
        )
    }

    internal fun createLaunches(launches: List<RocketLaunch>) {
        dbQuery.transaction {
            launches.forEach { launch ->
                insertLaunch(launch)
            }
        }
    }

    private fun insertLaunch(launch: RocketLaunch) {
        dbQuery.insertLaunch(
            flightNumber = launch.flightNumber.toLong(),
            missionName = launch.name,
            details = launch.details,
            launchSuccess = launch.launchSuccess ?: false,
            launchDateUTC = launch.dataUTC,
            patchUrlSmall = launch.links.patch?.small,
            patchUrlLarge = launch.links.patch?.large,
            articleUrl = launch.links.article
        )
    }
}

