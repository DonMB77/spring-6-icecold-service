package com.drifter.spring_6_icecold_service.services;

import guru.springframework.spring6restmvcapi.events.DrinkRequestEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DrinkRequestProcessorImpl implements DrinkRequestProcessor {
    @Override
    public void processDrinkRequest(DrinkRequestEvent drinkRequestEvent) {

        //log.debug("Processing drink request...");

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
