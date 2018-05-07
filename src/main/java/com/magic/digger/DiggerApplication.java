package com.magic.digger;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.magic.digger.feature.bestseller.controller.BestSellersController;
import com.magic.digger.feature.personalsales.controller.PersonalSalesController;

@SpringBootApplication public class DiggerApplication {

    private static final boolean runBestSeller=false;

    @Autowired private BestSellersController bestSellersController;
    @Autowired private PersonalSalesController personalSalesController;

    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(DiggerApplication.class, args)));
    }

    @Bean public ExitCodeGenerator runBestSellerApplication() {
        if( runBestSeller ) {
            List<String> cardList = Arrays.asList("Paladin affamé", "Enduire de venin", "Toucher de digital lunaire");

            try {
                bestSellersController.computeBestSellersCommande(cardList);
            } catch (IllegalStateException e) {
                return () -> 1;
            }
        }

        return () -> 0;
    }

    @Bean public ExitCodeGenerator runPersonalSalesApplication() {
        if( ! runBestSeller ) {
            try {
                personalSalesController.retrivePersonalSales();
            } catch (IllegalStateException|IOException e) {
                return () -> 1;
            }
        }

        return () -> 0;
    }
}
