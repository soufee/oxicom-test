package ci.ashamaz.mailerservice.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class MailSender {
    @Autowired
    val sender: JavaMailSender?= null
    private val logger: Logger = LoggerFactory.getLogger(MailSender::class.java)

    @Value("\${spring.mail.username}")
    private val username: String? = ""

    fun send(emailTo: String, subject: String, message: String) {
        val mailMessage = SimpleMailMessage()
        mailMessage.setFrom(username!!)
        mailMessage.setTo(emailTo)
        mailMessage.setSubject(subject)
        mailMessage.setText(message)
        logger.info("Sending a e-mail to $emailTo")
        sender?.send(mailMessage)
    }
}