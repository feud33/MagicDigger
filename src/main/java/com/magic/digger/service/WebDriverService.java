package com.magic.digger.service;

import org.springframework.stereotype.Service;

import com.magic.digger.dao.web.ApplicationWebDriver;
import com.magic.digger.dao.web.ApplicationWebDriverOption;

@Service
public class WebDriverService {

    private ApplicationWebDriver driver;

    public WebDriverService() {
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
