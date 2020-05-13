package ci.ashamaz.apiservice.service

import ci.ashamaz.apiservice.entity.Person

interface PersonService {
    fun getPersonById(id: String): Person?
    fun getAll(): List<Person>
    fun save(person: Person): Person?
    fun delete(person: Person)
    fun updateApprovalStatus(person: Person): Person?
}