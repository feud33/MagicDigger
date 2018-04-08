package com.magic.digger.dao.web;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Repository;

@Repository
public class CardSoldDao {
    public void getCardSolders(ApplicationWebDriver driver, List<String> cardList) {
        WebElement searchForm = driver.findElement(By.name("searchFor"));
        searchForm.sendKeys(cardList.get(0));

        WebElement searchButton = driver.findElement(By.id("search-btn"));
        searchButton.click();

        WebElement homeOnly = driver.findElement(By.name("productFilter[onlyHome]"));
        homeOnly.click();
    }

    public void setup(ApplicationWebDriver driver) {
        driver.get("https://www.cardmarket.com/fr/Magic");
    }

    public void findCard(ApplicationWebDriver driver, String card) {
        WebElement searchForm = driver.findElement(By.name("searchFor"));
        searchForm.sendKeys(card);

        WebElement searchButton = driver.findElement(By.id("search-btn"));
        searchButton.click();
    }
}
