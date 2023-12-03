package rockets

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketLaunch(
    @SerialName("flight_number")
    val flightNumber: Int,
    @SerialName("name")
    val name: String,
    @SerialName("date_utc")
    val dataUTC: String,
    @SerialName("details")
    val details: String?,
    @SerialName("success")
    val launchSuccess: Boolean?,
    @SerialName("links")
    val links : Links
)

@Serializable
data class Links(
    @SerialName("patch")
    val patch : Patch?,
    @SerialName("article")
    val article : String?
)

@Serializable
data class Patch(
    @SerialName("small")
    val small: String?,
    @SerialName("large")
    val large: String?
)