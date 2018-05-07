package com.magic.digger.feature.common.dao.web.cardmarket;

import static java.lang.Thread.sleep;

import java.util.ArrayList;
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

import com.magic.digger.feature.common.dao.web.ApplicationWebDriver;

@Service
public class CardmarketDao {

    public CardmarketDao() {
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

    public List<CardmarketCard> retrieveCardSolder(ApplicationWebDriver driver, String card) {
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
                .map(sellerLine -> sellerLine.findElements(By.tagName("tr")))
                .map(lineDetail -> new CardmarketCard(card, retrieveName(lineDetail.get(0)),
                                                        retrieveLanguage(lineDetail.get(1)),
                                                        retrievePrice(lineDetail.get(5)),
                                                        retrieveAvailable(lineDetail.get(6))))
                .collect(Collectors.toList());
    }

    public void login(ApplicationWebDriver driver) {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));

        WebElement loginDiv = driver.findElement(By.id("login"));

        WebElement usenameInput = loginDiv.findElement(By.name("username"));
        WebElement passwordInput = loginDiv.findElement(By.name("userPassword"));
        WebElement submitInput = loginDiv.findElement(By.id("login-btn"));

        usenameInput.sendKeys("feud33");
        passwordInput.sendKeys("MagicCardmarketBgehs294");
        submitInput.click();
    }

    public void getUserHomePage(ApplicationWebDriver driver, String user) {
        driver.get("https://www.cardmarket.com/fr/Magic/Users/" + user);
    }

    public void surfToListCards(ApplicationWebDriver driver) {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("H1_PageTitle")));

        WebElement articleUl = driver.findElement(By.className("catArticles-list"));

        List<WebElement> links = articleUl.findElements(By.tagName("a"));
        links.get(0).click();
    }

    public List<CardmarketCard> retrievePersonalCards(ApplicationWebDriver driver, List<CardmarketCard> cardmarketCards) {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("bupPaginator.innerNavBarCodeDiv")));

        WebElement tbodyCards = driver.findElement(By.className("MKMTable")).findElement(By.tagName("tbody"));
        for( WebElement lines : tbodyCards.findElements(By.tagName("tr"))) {
            List<WebElement> cols = lines.findElements(By.tagName("td"));
            CardmarketCard cardmarketCard = new CardmarketCard(retrieveForeignName(cols.get(1)),
                                                                retrieveLanguage(cols.get(4)),
                                                                retrievePrice(cols.get(8)),
                                                                retrieveAvailable(cols.get(9)));
            cardmarketCard.setComment(retrieveComment(cols.get(7)));
            cardmarketCards.add(cardmarketCard);
        }
        return new ArrayList<>();
    }

    public boolean hasNextPage(ApplicationWebDriver driver) {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("bupPaginator.navBarTopText")));

        WebElement next = driver.findElement(By.id("bupPaginator.pagIconNextPageTop"));
        boolean hasNext = next.isDisplayed();
        if( hasNext ) {
            next.click();
        }
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return hasNext;
    }

    private String retrievePrice(WebElement col) {
        String priceFullText = col.findElements(By.tagName("div")).get(1).getText();
        return priceFullText.substring(0, priceFullText.indexOf(' '));
    }

    private String retrieveForeignName(WebElement col) {
        return col.findElement(By.className("foreignText")).getText();
    }

    private String retrieveName(WebElement col) {
        return col.findElement(By.tagName("a")).getText();
    }

    private String retrieveLanguage(WebElement col) {
        String nationText = col.findElement(By.tagName("span")).getAttribute("onMouseOver");

        Pattern pattern = Pattern.compile("'(.*?)'");
        Matcher matcher = pattern.matcher(nationText);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }

    private String retrieveAvailable(WebElement col) {
        return col.getText();
    }

    private boolean isFrenchSeller(WebElement col) {
        WebElement nationIcon = col.findElements(By.className("icon")).get(0);
        return nationIcon.getAttribute("onMouseOver").contains("France");
    }

    private String retrieveComment(WebElement col) {
        return col.getText();
    }
}
