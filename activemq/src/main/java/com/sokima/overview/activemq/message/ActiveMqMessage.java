package com.sokima.overview.activemq.message;

import java.io.Serializable;

public record ActiveMqMessage(
        String destination,
        String owner,
        int numberToSend
) implements Serializable {
    public ActiveMqMessage(String destination, String owner) {
        this(destination, owner, 1);
    }

    @Override
    public String toString() {
        return "ActiveMqMessage[" +
                "owner=" + owner + '\'' +
                ", numberToSend=" + numberToSend +
                ']';
    }
}
