package com.sokima.overview.kafka.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    @Value("${message-brokers-overview.kafka.topic}")
    private String topic;

    @Bean
    public NewTopic kafkaOverviewTopic() {
        return TopicBuilder
                .name(topic)
                // To set partitions
//                .partitions(10)
                .build();
    }

}
