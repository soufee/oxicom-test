package ci.ashamaz.apiservice.service.impl

import ci.ashamaz.apiservice.service.IDGeneratorService
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

class IDGeneratorServiceImpl : IDGeneratorService {
    private val logger: Logger = LoggerFactory.getLogger(IDGeneratorServiceImpl::class.java)

    @Autowired
    private val client: DiscoveryClient? = null

    @Autowired
    private val rest: RestTemplate? = null

    override fun getUniqueID(): String {
        // абсолютно бесполезные 2 строчки кода --->
        if (client == null) throw IllegalStateException("Сервис получения уникальных ID не может быть определен")
        logger.debug("Найденный сервер {}", client.getInstances("id-generator").toString())
        // <----- Это все можно вынести на инфраструктуру, сделав DiscoveryClient notNull.

        val service: URI = client
            .getInstances("id-generator")
            ?.stream()
            ?.map { obj: ServiceInstance -> obj.uri }
            ?.findFirst()
            ?.map { s: URI -> s.resolve("/getuniqueid") }
            ?.get()
            ?: throw IllegalStateException("Сервис id-generator не найден")

        return rest?.getForEntity(service, String::class.java)?.body ?: ""
    }
}