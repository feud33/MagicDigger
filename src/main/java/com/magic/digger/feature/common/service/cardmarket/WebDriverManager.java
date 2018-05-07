package com.magic.digger.feature.common.service.cardmarket;

import com.magic.digger.feature.common.dao.web.ApplicationWebDriver;
import com.magic.digger.feature.common.dao.web.ApplicationWebDriverOption;

public class WebDriverManager {

    private ApplicationWebDriver driver;

    public WebDriverManager() {
        ApplicationWebDriverOption applicationWebDriverOption = new ApplicationWebDriverOption();
        driver = new ApplicationWebDriver(applicationWebDriverOption);
    }

    public void quit() {
        driver.close();
        driver.quit();
    }

    public ApplicationWebDriver getDriver() {
        return driver;
    }

    public void terminate() {
        driver.close();
        driver.quit();
    }
}
