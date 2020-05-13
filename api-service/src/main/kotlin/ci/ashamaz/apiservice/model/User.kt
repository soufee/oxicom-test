package ci.ashamaz.apiservice.model

data class User (
        var uuid: String? = "",
        val firstName: String,
        val lastName: String,
        val email: String,
        val dateBirth: String,
        val liveCity: String,
        val registrationCity: String = liveCity
        )