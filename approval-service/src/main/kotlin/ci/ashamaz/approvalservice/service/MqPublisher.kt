package ci.ashamaz.approvalservice.service

import ci.ashamaz.approvalservice.dto.ApprovalResult
import com.google.gson.Gson
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MqPublisher {
    @Autowired
    private val rabbitTemplate: AmqpTemplate? = null
    val gson = Gson()
    private val logger: Logger = LoggerFactory.getLogger(MqPublisher::class.java)

    private val exchangeName: String = "approve.exchange"

    fun publish(result: ApprovalResult) {
        val obj = gson.toJson(result)
        rabbitTemplate?.convertAndSend(exchangeName, "", obj)
        logger.debug("$obj sent")
    }
}