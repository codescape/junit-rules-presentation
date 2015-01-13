package de.codescape;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WebDriverTakingScreenshotTest {

    private static WebDriver driver;

    @Rule
    public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule(driver);

    @BeforeClass
    public static void beforeClass() {
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }

    @Test
    public void testThatSucceeds() {
        driver.get("http://junit.org/");
        assertThat(isTextPresent("JUnit"), is(true));
    }

    @Test
    public void testThatFails() {
        driver.get("http://junit.org/");
        assertThat(isTextPresent("Microsoft"), is(true));
    }

    private boolean isTextPresent(String text) {
        return driver.findElement(By.xpath("//*[contains(.,'" + text + "')]")).isDisplayed();
    }

}
