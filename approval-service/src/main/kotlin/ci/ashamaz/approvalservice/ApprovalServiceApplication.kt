package ci.ashamaz.approvalservice

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableRabbit
class ApprovalServiceApplication

fun main(args: Array<String>) {
    runApplication<ApprovalServiceApplication>(*args)
}
