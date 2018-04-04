package com.magic.digger;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import junit.framework.TestCase;

public class ChromeTest extends TestCase {

    private static ChromeDriverService service;
    private WebDriver driver;

    public static void createAndStartService() {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(
                        new File("C:\\Users\\feud7506\\AppData\\Local\\Google\\Chrome SxS\\Application\\chrome.exe"))
                .usingAnyFreePort()
                .build();
        //        CHROME_BIN=C:\Users\feud7506\AppData\Local\Google\Chrome SxS\Application\chrome.exe;PHANTOMJS_BIN=C:\My Program Files\phantomjs-2.1.1-windows\bin\phantomjs.exe;CHROMEDRIVER_BIN=C:\My Program Files\chromedriver_win32\chromedriver.exe
    }

    public static void createAndStopService() {
        service.stop();
    }

    public void createDriver() {
        driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());
    }

    public void quitDriver() {
        driver.quit();
    }

    public void testGoogleSearch() {
        //        final ChromeOptions chromeOptions = new ChromeOptions();
        //        Configuration configuration=new Configuration();
        //
        //        chromeOptions.setBinary(configuration.getPathToBinary("CHROME_BIN"));
        //
        //        System.setProperty("webdriver.chrome.driver", configuration.getPathToBinary("CHROMEDRIVER_BIN"));
        //
        //        WebDriver driver = new ChromeDriver(chromeOptions);
        //        driver.manage().deleteAllCookies();
        //        driver.get("https://www.cardmarket.com/fr/Magic");
        //        driver.manage ().timeouts ().implicitlyWait (10, TimeUnit.SECONDS);
        //        driver.manage().window().maximize();
        //        try {
        //            Thread.sleep(2000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //        assertEquals("webdriver - Google Search", driver.getTitle());
        //        driver.quit();
    }
}
