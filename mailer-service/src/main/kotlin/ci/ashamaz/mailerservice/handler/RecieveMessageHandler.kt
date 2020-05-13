package ci.ashamaz.mailerservice.handler

import ci.ashamaz.mailerservice.dto.ApprovalResult
import ci.ashamaz.mailerservice.service.MailSender
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
    @Autowired
    private val mailSender: MailSender? = null

    private val gson = Gson()
    private val logger: Logger = LoggerFactory.getLogger(RecieveMessageHandler::class.java)

    @RabbitListener(queues = ["approve1-1"])
    @RabbitHandler
    fun handleMessage(messageBody: String?) {
        logger.debug("Handling message!... $messageBody")
        val result = gson.fromJson(messageBody, ApprovalResult::class.java)
        val user = result.user
        val text = """
            Hello, ${user.firstName}
            ${if (result.approved) "Your application with ID ${user.uuid} is approved" else "Your application with ID ${user.uuid} is rejected"}
        """.trimIndent()
        mailSender?.send(user.email, "Approval status", text)
    }
}