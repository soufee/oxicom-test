package ci.ashamaz.approvalservice.dto

import java.io.Serializable

// форматирование
data class User(
    val uuid: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val dateBirth: String,
    val liveCity: String,
    val registrationCity: String = liveCity
) : Serializable