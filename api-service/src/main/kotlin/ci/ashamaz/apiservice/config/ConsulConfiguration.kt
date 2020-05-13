package ci.ashamaz.apiservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class ConsulConfiguration {
    @Bean
    fun template(): RestTemplate? {
        return RestTemplate()
    }
}