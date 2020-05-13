package ci.ashamaz.mailerservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MailerServiceApplication

fun main(args: Array<String>) {
    runApplication<MailerServiceApplication>(*args)
}
