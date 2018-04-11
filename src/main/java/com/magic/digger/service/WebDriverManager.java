package com.magic.digger.service;

import org.springframework.stereotype.Service;

import com.magic.digger.dao.web.driver.ApplicationWebDriver;
import com.magic.digger.dao.web.driver.ApplicationWebDriverOption;

@Service
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
