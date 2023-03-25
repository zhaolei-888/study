package com.example.esdemo.service;

import com.example.esdemo.api.KafkaProducerServiceInterface;
import com.example.esdemo.command.SendMessageCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class KafkaProducerService implements KafkaProducerServiceInterface {
    @Override
    public boolean sendMessage(SendMessageCommand command) {
        Map<Long, String> proSource = command.getProSource();
        if(proSource != null){
            proSource.put(2L, "特殊策略");
        }
        return true;
    }
}
