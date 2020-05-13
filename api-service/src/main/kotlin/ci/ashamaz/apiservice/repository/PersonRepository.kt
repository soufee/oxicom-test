package ci.ashamaz.apiservice.repository

import ci.ashamaz.apiservice.entity.Person
import org.springframework.data.jpa.repository.JpaRepository


interface PersonRepository : JpaRepository<Person, String> {
    fun getById(id: String): Person?

}