package com.magic.digger.service.webdriver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

@Component
public class Configuration {
    public String getPathToBinary(String envName) {
        final String readValue = System.getenv(envName);

        if (Strings.isNullOrEmpty(readValue)) {
            throw new IllegalStateException("La variable d'environnement " + envName + " n'est pas définie.");
        }
        Path path = Paths.get(readValue);
        if (!Files.isReadable(path)) {
            throw new IllegalStateException(
                    "La variable d'environnement " + envName + " n'est pas un emplacement accessible en lecture (=" +
                            readValue + ").");
        }
        if (!Files.isRegularFile(path)) {
            throw new IllegalStateException(
                    "La variable d'environnement " + envName + " ne correspond pas à un fichier (=" +
                            readValue + ").");
        }
        return path.toString();
    }
}
