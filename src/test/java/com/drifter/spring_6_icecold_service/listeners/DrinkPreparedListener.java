package com.drifter.spring_6_icecold_service.listeners;

import com.drifter.spring_6_icecold_service.config.KafkaConfig;
import guru.springframework.spring6restmvcapi.events.DrinkPreparedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DrinkPreparedListener {

    public AtomicInteger coldDrinkMessageCounter = new AtomicInteger(0);

    @KafkaListener(topics = KafkaConfig.DRINK_PREPARED_TOPIC, groupId = "cold-listener")
    public void listen(DrinkPreparedEvent event) {
        System.out.println("I'm listening");

        coldDrinkMessageCounter.incrementAndGet();
    }
}
