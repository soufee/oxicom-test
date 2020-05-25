package ci.ashamaz.mailerservice.handler

import ci.ashamaz.mailerservice.dto.ApprovalResult
import ci.ashamaz.mailerservice.mixins.LoggerMixin
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

class ReceiveMessageHandler(
    // Autowire - моветон. Да и конструктор в котлине позволяет инжектить более локанично
    private val mailSender: MailSender
): LoggerMixin {

    // Почему не Bean?
    private val gson = Gson()

    // Можно сделать по другому (см. LoggerMixin)
    private val logger: Logger = LoggerFactory.getLogger(ReceiveMessageHandler::class.java)

    // Kotlin way
    @RabbitListener(queues = ["approve1-1"])
    @RabbitHandler
    fun handleMessage(messageBody: String?) = gson.fromJson(messageBody, ApprovalResult::class.java)
        ?.let { result ->
            Pair(
                result.user,
                """
                        Hello, ${result.user.firstName}
                        ${if (result.approved) "Your application with ID ${result.user.uuid} is approved" else "Your application with ID ${result.user.uuid} is rejected"}
                    """.trimIndent()
            )
        }
        ?.also { (user, text) ->
            mailSender.send(user.email, "Approval status", text)
        }
        .apply {
            logger().debug("Handling message!... $messageBody")
        }
}