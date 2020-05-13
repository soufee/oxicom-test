package ci.ashamaz.apiservice.entity

import java.time.LocalDate
import javax.persistence.*


@Entity
@Table(name = "person")
class Person {
    @Id
    @Column(name="id", unique = true, nullable = false)
    var id: String? = null
    @Column(name = "first_name")
    var firstName: String? = null
    @Column(name = "last_name")
    var lastName: String? = null
    @Column(name = "email")
    var email: String? = null
    @Column(name = "date_birth")
    var dateBirth: LocalDate?=null
    @Column(name = "live_city")
    var liveCity: String?=null
    @Column(name = "registration_city")
    var registrationCity: String? = null
    @Column(name = "approved")
    var isApproved: Boolean = false
}