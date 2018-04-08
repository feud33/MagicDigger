package com.magic.digger.dao.web.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationWebDriver extends ChromeDriver {

    public ApplicationWebDriver(ApplicationWebDriverOption applicationWebDriverOption) {
        super(applicationWebDriverOption);
        this.manage().deleteAllCookies();
        this.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.manage().window().maximize();

    }
}
