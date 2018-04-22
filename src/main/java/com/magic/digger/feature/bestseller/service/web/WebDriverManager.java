package com.magic.digger.feature.bestseller.service.web;

public class WebDriverManager {

    //    private ApplicationWebDriver driver;

    public WebDriverManager() {
/*
        ApplicationWebDriverOption applicationWebDriverOption = new ApplicationWebDriverOption();
        driver = new ApplicationWebDriver(applicationWebDriverOption);
*/
    }

    public void quit() {
/*
        driver.close();
        driver.quit();
*/
    }

/*
    public ApplicationWebDriver getDriver() {
        return driver;
    }
*/

    public void terminate() {
        //        driver.close();
        //        driver.quit();
        System.out.println("pok");
    }
}
