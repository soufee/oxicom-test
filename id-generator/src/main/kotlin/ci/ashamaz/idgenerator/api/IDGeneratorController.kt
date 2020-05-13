package ci.ashamaz.idgenerator.api

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class IDGeneratorController {
    private val logger: Logger = LoggerFactory.getLogger(IDGeneratorController::class.java)
    @GetMapping("/getuniqueid")
    fun getVerification(): String {
        val uuid = UUID.randomUUID().toString()
        logger.info("Genrated ${uuid}")
        return uuid

    }
}