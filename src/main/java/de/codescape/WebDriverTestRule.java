package de.codescape;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverTestRule implements TestRule {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public WebDriver getDriver() {
        return driver.get();
    }

    @Override
    public Statement apply(final Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                createDriver();
                try {
                    statement.evaluate();
                } finally {
                    closeDriverSafely();
                }
            }
        };
    }

    private void createDriver() {
        driver.set(new FirefoxDriver());
    }

    private void closeDriverSafely() {
        try {
            driver.get().quit();
        } catch (Exception e) {
            // do not fail when closing browser fails
        }
    }

}
