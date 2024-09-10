package com.finefine.run;

import com.finefine.Rule;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ServiceLoader;

import static java.lang.Thread.sleep;

public class App {

    public static void main(String[] args) throws  InterruptedException {

        for (int i = 0; i < 100; i++) {
            try {
                invokeRule(i);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            sleep(1000);
        }
    }

    static void invokeRule(int i) throws MalformedURLException {
        URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("http://127.0.0.1:8080/spi-concrete-1.0-SNAPSHOT.jar")},Thread.currentThread().getContextClassLoader());
        ServiceLoader<Rule> serviceLoader = ServiceLoader.load(Rule.class,classLoader);
        serviceLoader.forEach(rule -> rule.applyRule("Hello:" + i));
        try {
            classLoader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
