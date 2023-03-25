package com.example.esdemo.api;

import com.example.esdemo.command.SendMessageCommand;

public interface KafkaProducerServiceInterface {

    boolean sendMessage(SendMessageCommand command);
}
