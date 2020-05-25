package ci.ashamaz.mailerservice.mixins

import ci.ashamaz.mailerservice.handler.ReceiveMessageHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface LoggerMixin {

    fun logger(): Logger = LoggerFactory.getLogger(this::class.java)

}