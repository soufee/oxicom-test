package ci.ashamaz.apiservice.handler

import ci.ashamaz.apiservice.model.ApprovalResult
import ci.ashamaz.apiservice.service.PersonService
import com.google.gson.Gson
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@EnableRabbit
class RecieveMessageHandler {
    val gson = Gson()
    private val logger: Logger = LoggerFactory.getLogger(RecieveMessageHandler::class.java)

    @Autowired
    val personService: PersonService? = null

    @RabbitListener(queues = ["approve1-2"])
    @RabbitHandler
    fun handleMessage(messageBody: String?) {
        logger.debug("Handling message back $messageBody")
        val result = gson.fromJson(messageBody, ApprovalResult::class.java)
        val uuid = result.user.uuid ?: ""
        val user = personService?.getPersonById(uuid)
        if (user != null) {
            user.isApproved = result.approved
            val res = personService?.save(user)
            logger.info("Saved ${res}")
        }
    }
}