package com.sokima.overview.rabbitmq.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitMqConfiguration {

    @Value("${message-brokers-overview.rabbitmq.queue.name.simple}")
    private String simpleQueueName;

    @Value("${message-brokers-overview.rabbitmq.queue.name.json}")
    private String jsonQueueName;

    @Value("${message-brokers-overview.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${message-brokers-overview.rabbitmq.routing-key.simple}")
    private String simpleRoutingKey;

    @Value("${message-brokers-overview.rabbitmq.routing-key.json}")
    private String jsonRoutingKey;

    @Bean
    public Queue simpleQueue() {
        return new Queue(simpleQueueName);
    }

    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueueName);
    }

    @Primary
    @Bean
    public Exchange topicExchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding simpleBinding(@Qualifier("simpleQueue") Queue queue, Exchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(simpleRoutingKey)
                .noargs();
    }

    @Bean
    public Binding jsonBinding(@Qualifier("jsonQueue") Queue queue, Exchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(jsonRoutingKey)
                .noargs();
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate customRabbitMqTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}
