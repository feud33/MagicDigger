package com.magic.digger.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

@Service
public class CardSoldService {
    public CardSoldService() {
    }

    private ChromeDriver setUp(Properties properties) {
        System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
        String binaryPath = properties.getProperty("CHROME_BIN");

        if (binaryPath == null) {
            throw new RuntimeException("Missing property : " + "CHROME_BIN");
        }

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.setBinary(binaryPath);
        options.addArguments("--headless");
        options.addArguments("--user-agent="
                + "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
        return new ChromeDriver(options);
    }

    public void getCardSolders(List<String> cardList) {
        System.out.println("Hello");
    }

    //        bestSellersControler.computeBestSellersCommande(seekedCards);
    //		SpringApplication.run(DiggerApplication.class, args);
    //        Configuration configuration=new Configuration();
    //
    ////        System.setProperty("webdriver.gecko.driver", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
    //
    //        FirefoxDriver driver = new FirefoxDriver();
    //
    //        String baseUrl = "http://google.com";
    //
    //        driver.get(baseUrl);
    //

    //		final ChromeOptions chromeOptions = new ChromeOptions();
    //		Configuration configuration=new Configuration();
    //
    //		chromeOptions.setBinary(configuration.getPathToBinary("CHROME_BIN"));
    //
    //		System.setProperty("webdriver.chrome.driver", configuration.getPathToBinary("CHROMEDRIVER_BIN"));
    //
    //		ChromeDriver driver = new ChromeDriver(chromeOptions);
    //		driver.manage().deleteAllCookies();
    //		driver.get("https://www.cardmarket.com/fr/Magic");
    //        driver.manage ().timeouts ().implicitlyWait (10, TimeUnit.SECONDS);
    //        driver.manage().window().maximize();
    //        try {
    //            Thread.sleep(2000);
    //        } catch (InterruptedException e) {
    //            e.printStackTrace();
    //        }
    //
    //        driver.quit();

}
