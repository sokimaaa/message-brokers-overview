package com.sokima.overview.rabbitmq.message;

import java.io.Serializable;

public record RabbitMqMessage(
        String destination,
        String owner,
        int numberToSend
) implements Serializable {
    public RabbitMqMessage(String destination, String owner) {
        this(destination, owner, 1);
    }

    @Override
    public String toString() {
        return "RabbitMqMessage[" +
                "owner='" + owner + '\'' +
                ", numberToSend=" + numberToSend +
                ']';
    }
}
