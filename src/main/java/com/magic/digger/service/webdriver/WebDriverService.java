package com.magic.digger.service.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

@Service
public class WebDriverService {

    public WebDriverService() {
    }

    public void create() {
        final ChromeOptions chromeOptions = new ChromeOptions();
        Configuration configuration = new Configuration();

        chromeOptions.setBinary(configuration.getPathToBinary("CHROME_BIN"));

        System.setProperty("webdriver.chrome.driver", configuration.getPathToBinary("CHROMEDRIVER_BIN"));

        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().deleteAllCookies();
        driver.get("https://www.cardmarket.com/fr/Magic");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.close();
        driver.quit();
    }
}
