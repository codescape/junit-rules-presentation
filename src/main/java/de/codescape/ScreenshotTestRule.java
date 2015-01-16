package de.codescape;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * JUnit {@link TestRule} that takes a screenshot with the current {@link WebDriver} instance for a failing test.
 */
public class ScreenshotTestRule extends TestWatcher {

    private static final String TARGET_DIRECTORY = "./target/screenshots";

    private final WebDriver driver;

    public ScreenshotTestRule(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void failed(Throwable e, Description description) {
        captureScreenshot(description.getClassName() + "." + description.getMethodName());
        super.failed(e, description);
    }

    private void captureScreenshot(String fileName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(TARGET_DIRECTORY + "/" + fileName + ".png"));
        } catch (Exception e) {
            // do not fail the test if we cannot create the screenshot
        }
    }

}
