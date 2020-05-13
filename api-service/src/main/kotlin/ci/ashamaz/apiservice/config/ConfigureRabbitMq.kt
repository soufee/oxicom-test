package ci.ashamaz.apiservice.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ConfigureRabbitMq {
    val queueName1 = "approve1-1"
    val queueName2 = "approve1-2"
    val exchangeName = "approve.exchange"

    @Bean
    fun queue1(): Queue? {
        return Queue(queueName1, true, false, false)
    }

    @Bean
    fun queue2(): Queue? {
        return Queue(queueName2, true, false, false)
    }

    @Bean
    fun exchange(): FanoutExchange? {
        return FanoutExchange(exchangeName)
    }

    @Bean
    fun binding1(): Binding? {
        return BindingBuilder.bind(queue1()).to(exchange())
    }
    @Bean
    fun binding2(): Binding? {
        return BindingBuilder.bind(queue2()).to(exchange())
    }
}