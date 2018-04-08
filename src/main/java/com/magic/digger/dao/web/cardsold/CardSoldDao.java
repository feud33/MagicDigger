package com.magic.digger.dao.web.cardsold;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Repository;

import com.magic.digger.dao.web.driver.ApplicationWebDriver;

@Repository
public class CardSoldDao {
    public void setup(ApplicationWebDriver driver) {
        driver.get("https://www.cardmarket.com/fr/Magic");
    }

    public void findCard(ApplicationWebDriver driver, String card) {
        WebElement searchForm = driver.findElement(By.name("searchFor"));
        searchForm.sendKeys(card);

        WebElement searchButton = driver.findElement(By.id("search-btn"));
        searchButton.click();
    }

    public void filterCard(ApplicationWebDriver driver) {
        WebElement articleFilterForm = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("articleFilterForm")));

        Select languageSelect = new Select(articleFilterForm.findElement(By.name("productFilter[idLanguage][]")));
        languageSelect.selectByValue("1");
        languageSelect.selectByValue("2");

        articleFilterForm.submit();
    }

    public List<ForSale> retrieveCardSolder(ApplicationWebDriver driver, String cardName) {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("nameHeader")));

        // loop to display all the sellers
        if (driver.findElements(By.id("moreDiv")).isEmpty()) {
            driver.findElement(By.id("moreDiv")).click();
            (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.id("ajaxLoadermoreDiv")));
        }

        WebElement sellers = driver.findElement(By.id("articlesTable"));

        return sellers.findElements(By.tagName("tr")).stream()
                .skip(1)
                .filter(this::isFrenchSeller)
                .map(sellerLine -> new ForSale(cardName, retrieveName(sellerLine), retrieveLanguage(sellerLine),
                        retrievePrice(sellerLine), retrieveAvailable(sellerLine)))
                .collect(Collectors.toList());
    }

    private int retrievePrice(WebElement line) {
        WebElement priceNode = line.findElement(By.className("Price"));
        String priceFullTExt = priceNode.findElements(By.tagName("div")).get(1).getText();
        String stringPrice = priceFullTExt.substring(0, priceFullTExt.indexOf(' '));

        int price = 0;
        try {
            price = Integer.parseInt(stringPrice);
        } catch (NumberFormatException e) {
            // do nothing, price to 0
        }
        return price;
    }

    private String retrieveName(WebElement line) {
        WebElement seller = line.findElement(By.className("Seller"));
        return seller.findElement(By.tagName("a")).getText();
    }

    private LanguageEnum retrieveLanguage(WebElement line) {
        WebElement seller = line.findElement(By.className("Language"));
        WebElement nationText = seller.findElement(By.tagName("span"));
        if (nationText.getAttribute("onMouseOver").contains("Français")) {
            return LanguageEnum.FRENCH;
        } else {
            return LanguageEnum.ENGLISH;
        }
    }

    private int retrieveAvailable(WebElement line) {
        String stringAvailable = line.findElement(By.className("Available")).getText();

        int quantity = 0;
        try {
            quantity = Integer.parseInt(stringAvailable);
        } catch (NumberFormatException e) {
            // do nothing, quantity to 0
        }
        return quantity;
    }

    private boolean isFrenchSeller(WebElement line) {
        WebElement seller = line.findElement(By.className("Seller"));
        WebElement nationIcon = seller.findElements(By.className("icon")).get(0);
        return nationIcon.getAttribute("onMouseOver").contains("France");
    }
}
