package ci.ashamaz.apiservice.service.impl

import ci.ashamaz.apiservice.service.ApprovalService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.ServiceInstance
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI

@Service
@EnableDiscoveryClient

class ApprovalServiceImpl : ApprovalService {

    private val logger: Logger = LoggerFactory.getLogger(ApprovalServiceImpl::class.java)

    @Autowired
    private val rest: RestTemplate? = null

    @Autowired
    private val client: DiscoveryClient? = null


    override fun sendToApprove(obj: Any) {
        logger.debug("Отправляем на утверждение $obj")
        if (client == null) throw IllegalStateException("Сервис одобрений заявок не может быть определен")
        logger.debug("Найденный сервер {}", client.getInstances("approval-service").toString())

        val service: URI = client.getInstances("approval-service").stream().map { o:ServiceInstance -> o.uri }
                ?.findFirst()?.map { s: URI -> s.resolve("/approve") }?.get()
                ?: throw IllegalStateException("Сервис id-generator не найден")
        val result = rest?.postForEntity(service, obj, String::class.java)?.body?:"Ошибка отправки заявки в сервис одобрения"
        logger.info(result)
    }


}