package ci.ashamaz.apiservice.service.impl

import ci.ashamaz.apiservice.entity.Person
import ci.ashamaz.apiservice.repository.PersonRepository
import ci.ashamaz.apiservice.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl : PersonService {

    @Autowired
    val personRepository: PersonRepository? = null

    override fun getPersonById(id: String): Person? {
        return personRepository?.getById(id)
    }

    override fun getAll(): List<Person> {
        return personRepository?.findAll().orEmpty()
    }

    override fun save(person: Person): Person? {
        return personRepository?.save(person)
    }

    override fun delete(person: Person) {
        personRepository?.delete(person)
    }

    override fun updateApprovalStatus(person: Person): Person? {
        val p = getPersonById(person.id ?: "")
        if (p != null) {
            p.isApproved = person.isApproved
            return personRepository?.save(p)
        } else {
            throw IllegalArgumentException("Пользователь не найден в базе")
        }

    }
}