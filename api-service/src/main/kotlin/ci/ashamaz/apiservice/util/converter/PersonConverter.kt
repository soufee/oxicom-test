package ci.ashamaz.apiservice.util.converter

import ci.ashamaz.apiservice.entity.Person
import ci.ashamaz.apiservice.model.User
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Service
class PersonConverter : Converter<User, Person> {
    override fun convert(user: User): Person? {
        val person = Person()
        person.firstName = user.firstName
        person.lastName = user.lastName
        person.email = user.email
        person.dateBirth = parseDate(user.dateBirth)
        person.liveCity = user.liveCity
        person.registrationCity = user.registrationCity
        person.isApproved = false
        return person
    }

    private fun parseDate(date: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.parse(date, formatter)
    }
}