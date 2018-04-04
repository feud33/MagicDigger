package com.magic.digger;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.magic.digger.controller.BestSellersController;

@SpringBootApplication
public class DiggerApplication {

    @Autowired
    private BestSellersController bestSellersController;

    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(DiggerApplication.class, args)));
    }

    @Bean
    public ExitCodeGenerator runApplication() {
        List<String> cardList = Arrays.asList("Enduire de venin", "Toucher de digital lunaire");

        try {
            bestSellersController.computeBestSellersCommande(cardList);
        } catch (IllegalStateException e) {
            return () -> 1;
        }

        return () -> 0;
    }
}
