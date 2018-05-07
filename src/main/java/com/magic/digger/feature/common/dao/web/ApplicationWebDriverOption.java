package com.magic.digger.feature.common.dao.web;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.chrome.ChromeOptions;

import com.google.common.base.Strings;

public class ApplicationWebDriverOption extends ChromeOptions {
    public ApplicationWebDriverOption() {
        super();

        System.setProperty("webdriver.chrome.driver", getPathToBinary("CHROMEDRIVER_BIN"));

        this.setBinary(getPathToBinary("CHROME_BIN"));
    }

    private String getPathToBinary(String envName) {

        final String readValue = System.getenv(envName);

        if (Strings.isNullOrEmpty(readValue)) {
            throw new IllegalStateException(envName + " should be defined");
        }
        Path path = Paths.get(readValue);
        if (!Files.isReadable(path)) {
            throw new IllegalStateException(
                    envName + " is not a readable n'est pas un emplacement accessible en lecture (=" +
                            readValue + ").");
        }
        if (!Files.isRegularFile(path)) {
            throw new IllegalStateException(
                    "La variable d'environnement " + envName + " ne correspond pas à un fichier (=" +
                            readValue + ").");
        }
        System.out.println(path.toString());
        return path.toString();
    }
}