package com.sokima.overview.rabbitmq.producer;

public interface RabbitMqMessageProducer<T> {
    void sendMessage(T message);
}
