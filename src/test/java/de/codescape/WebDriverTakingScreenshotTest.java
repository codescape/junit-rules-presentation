package de.codescape;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WebDriverTakingScreenshotTest {

    @ClassRule
    public static WebDriverTestRule webDriverTestRule = new WebDriverTestRule();

    @Rule
    public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule(webDriverTestRule.getDriver());

    @Test
    public void testThatSucceeds() {
        driver().get("http://junit.org/");
        assertThat(isTextPresent("JUnit"), is(true));
    }

    @Test
    public void testThatFails() {
        driver().get("http://junit.org/");
        assertThat(isTextPresent("Microsoft"), is(true));
    }

    private boolean isTextPresent(String text) {
        return driver().findElement(By.xpath("//*[contains(.,'" + text + "')]")).isDisplayed();
    }

    private WebDriver driver() {
        return webDriverTestRule.getDriver();
    }

}
