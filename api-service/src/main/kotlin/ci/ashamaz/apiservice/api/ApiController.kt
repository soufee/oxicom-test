package ci.ashamaz.apiservice.api

import ci.ashamaz.apiservice.entity.Person
import ci.ashamaz.apiservice.model.UniversalResponse
import ci.ashamaz.apiservice.model.User
import ci.ashamaz.apiservice.service.ApprovalService
import ci.ashamaz.apiservice.service.IDGeneratorService
import ci.ashamaz.apiservice.service.PersonService
import com.google.gson.Gson
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class ApiController {
    private val logger: Logger = LoggerFactory.getLogger(ApiController::class.java)

    @Autowired
    val personConverter: Converter<User, Person>? = null

    @Autowired
    val personService: PersonService? = null

    @Autowired
    val idGeneratorService: IDGeneratorService? = null

    @Autowired
    val approvalService: ApprovalService? = null

    val gson = Gson()

    @ResponseBody
    @RequestMapping(value = ["/send-verification"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun sendVerification(@RequestBody user: User?): UniversalResponse {
        logger.debug("POST method")
        val uniqueId: String = idGeneratorService?.getUniqueID() ?: throw Exception("Не удалось получить uniqeID")
        if (user != null) {
            val person = personConverter?.convert(user)
            if (person != null) {
                person.id = uniqueId
                personService?.save(person)
                user.uuid = uniqueId
                sendSimpleMessage(user)
                return UniversalResponse(200, "ID запроса ${person.id}")
            }
        }
        return UniversalResponse(400, "Неверный запрос. Тело запроса не распоознано")
    }

    private fun sendSimpleMessage(user: User) {
        approvalService?.sendToApprove(gson.toJson(user))
    }

    @GetMapping("/check-verification-status/{id}")
    fun getVerification(@PathVariable id: String): UniversalResponse {
        logger.debug("GET method")
        val user = personService?.getPersonById(id)
        return if (user == null) {
            UniversalResponse(400, "Неверный запрос. Заявка с ID  ${id} не найдена")
        } else
            UniversalResponse(200, "Статус заявки с ID запроса ${id} : ${user.isApproved}")

    }


}