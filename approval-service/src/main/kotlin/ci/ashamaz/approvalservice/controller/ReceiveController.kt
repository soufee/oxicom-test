package ci.ashamaz.approvalservice.controller

import ci.ashamaz.approvalservice.dto.ApprovalResult
import ci.ashamaz.approvalservice.dto.User
import ci.ashamaz.approvalservice.service.MqPublisher
import ci.ashamaz.approvalservice.validate.Validator
import com.google.gson.Gson
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ReceiveController {
    private val gson = Gson()
    private val logger: Logger = LoggerFactory.getLogger(ReceiveController::class.java)

    @Autowired
    val validator: Validator? = null

    @Autowired
    val mqPublisher: MqPublisher? = null

    @PostMapping("/approve")
    fun sendMessage(@RequestBody obj: String): String? {
        logger.debug("We have got a message: $obj")

        val user = gson.fromJson(obj, User::class.java)
        val approved = validator?.validate(user) ?: false
        val result = ApprovalResult(user, approved)
        mqPublisher?.publish(result)
        return ("$user обработан. Результат $approved")
    }
}