package com.drifter.spring_6_icecold_service.listeners;

import com.drifter.spring_6_icecold_service.config.KafkaConfig;
import com.drifter.spring_6_icecold_service.services.DrinkRequestProcessor;
import guru.springframework.spring6restmvcapi.events.DrinkPreparedEvent;
import guru.springframework.spring6restmvcapi.events.DrinkRequestEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DrinkRequestListener {

    private final DrinkRequestProcessor drinkRequestProcessor;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public DrinkRequestListener(KafkaTemplate<String, Object> kafkaTemplate, DrinkRequestProcessor drinkRequestProcessor) {
        this.kafkaTemplate = kafkaTemplate;
        this.drinkRequestProcessor = drinkRequestProcessor;
    }

    @KafkaListener(groupId = "IceColdListener", topics = KafkaConfig.DRINK_REQUEST_ICE_COLD_TOPIC)
    public void listenDrinkRequest(DrinkRequestEvent drinkRequestEvent) {
        System.out.println("I am listening - drink request");

        drinkRequestProcessor.processDrinkRequest(drinkRequestEvent);

        kafkaTemplate.send(KafkaConfig.DRINK_PREPARED_TOPIC, DrinkPreparedEvent.builder()
                .beerOrderLine(drinkRequestEvent.getBeerOrderLineDTO())
                .build());
    }
}
