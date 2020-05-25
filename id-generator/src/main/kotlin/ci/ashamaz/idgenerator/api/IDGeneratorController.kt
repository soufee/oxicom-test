package ci.ashamaz.idgenerator.api

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class IDGeneratorController {
    private val logger: Logger = LoggerFactory.getLogger(IDGeneratorController::class.java)

    // getuniqueid - нейминг не очень. Вы сможете прочитать generatenewidforverificatedautomatedoperationbycategory{id} ?
    @GetMapping("/getuniqueid")
    fun getVerification(): String = UUID.randomUUID().toString()
        .apply { logger.info("Generated $this") }
}