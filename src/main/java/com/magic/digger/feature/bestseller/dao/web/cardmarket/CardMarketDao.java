package com.magic.digger.feature.bestseller.dao.web.cardmarket;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import com.magic.digger.feature.bestseller.dao.web.driver.ApplicationWebDriver;

@Service
public class CardMarketDao {

    public CardMarketDao() {
    }

    public void setup(ApplicationWebDriver driver) {
        driver.get("https://www.cardmarket.com/fr/Magic");
    }

    public void surfTofindCard(ApplicationWebDriver driver, String card) {
        WebElement searchForm = driver.findElement(By.name("searchFor"));
        searchForm.sendKeys(card);

        WebElement searchButton = driver.findElement(By.id("search-btn"));
        searchButton.click();
    }

    public void surfTofilterCard(ApplicationWebDriver driver) {
        WebElement articleFilterForm = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("articleFilterForm")));

        Select languageSelect = new Select(articleFilterForm.findElement(By.name("productFilter[idLanguage][]")));
        languageSelect.selectByValue("1");
        languageSelect.selectByValue("2");

        articleFilterForm.submit();
    }

    public List<CMForSale> retrieveCardSolder(ApplicationWebDriver driver) {
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
                .map(sellerLine -> new CMForSale(retrieveName(sellerLine), retrieveLanguage(sellerLine),
                        retrievePrice(sellerLine), retrieveAvailable(sellerLine)))
                .collect(Collectors.toList());
    }

    private String retrievePrice(WebElement line) {
        WebElement priceNode = line.findElement(By.className("Price"));
        String priceFullTExt = priceNode.findElements(By.tagName("div")).get(1).getText();
        return priceFullTExt.substring(0, priceFullTExt.indexOf(' '));
    }

    private String retrieveName(WebElement line) {
        WebElement seller = line.findElement(By.className("Seller"));
        return seller.findElement(By.tagName("a")).getText();
    }

    private String retrieveLanguage(WebElement line) {
        WebElement seller = line.findElement(By.className("Language"));
        String nationText = seller.findElement(By.tagName("span")).getAttribute("onMouseOver");

        Pattern pattern = Pattern.compile("'(.*?)'");
        Matcher matcher = pattern.matcher(nationText);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }

    private String retrieveAvailable(WebElement line) {
        return line.findElement(By.className("Available")).getText();
    }

    private boolean isFrenchSeller(WebElement line) {
        WebElement seller = line.findElement(By.className("Seller"));
        WebElement nationIcon = seller.findElements(By.className("icon")).get(0);
        return nationIcon.getAttribute("onMouseOver").contains("France");
    }
}
