package ro.tuc.ds2020;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;

@Configuration
@EnableRabbit
public class RabbitMQConfiguration {

    static final String queueName = "queue";
    static final String routingKey = "rabbit";

    @Bean
    Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("rabbitMQ");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }


}
