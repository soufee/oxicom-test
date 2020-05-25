package ci.ashamaz.mailerservice.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class MailSender(
    // Autowire - моветон. Да и конструктор в котлине позволяет инжектить более локанично
    private val sender: JavaMailSender
) {

    private val logger: Logger = LoggerFactory.getLogger(MailSender::class.java)

    @Value("\${spring.mail.username}")
    private val username: String = "no-reply"

    // Java Style vs. Kotlin way
    fun send(emailTo: String, subject: String, message: String) = SimpleMailMessage()
        .apply {
            setFrom(username) // за force unwrapper(!!) обычно принято отрубать руки
            setTo(emailTo)
            setSubject(subject)
            setText(message)
        }
        .also { sender.send(it) }
        .apply {
            logger.info("Sending a e-mail to ${this.to}")
        }
}