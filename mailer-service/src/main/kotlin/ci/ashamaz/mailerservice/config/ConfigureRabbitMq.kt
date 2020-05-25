package ci.ashamaz.mailerservice.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// expression body почему не использовали? return - хлам
@Configuration
class ConfigureRabbitMq {
    val queueName1 = "approve1-1"
    val queueName2 = "approve1-2"
    val exchangeName = "approve.exchange"

    @Bean
    fun queue1() = Queue(queueName1, true, false, false)

    @Bean
    fun queue2() = Queue(queueName2, true, false, false)

    @Bean
    fun exchange() = FanoutExchange(exchangeName)

    @Bean
    fun binding1() = BindingBuilder.bind(queue1()).to(exchange())
    @Bean
    fun binding2() = BindingBuilder.bind(queue2()).to(exchange())
}