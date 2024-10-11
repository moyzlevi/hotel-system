package com.molevi.hotel_service.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

@Service
public record ProducerSenderService(KafkaTemplate<String, String> kafkaTemplate) {
    public void send(String topicRequest, String requestObject) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topicRequest, requestObject);
        record.headers().add(new RecordHeader(KafkaHeaders.TOPIC, topicRequest.getBytes()));
        kafkaTemplate.send(record);
    }
}
