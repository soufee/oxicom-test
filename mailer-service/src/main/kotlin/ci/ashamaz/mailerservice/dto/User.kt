package ci.ashamaz.mailerservice.dto

import java.io.Serializable

data class User(
    val uuid: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val dateBirth: String,
    val liveCity: String,
    val registrationCity: String = liveCity
) : Serializable