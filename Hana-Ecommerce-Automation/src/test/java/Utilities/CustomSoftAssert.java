package Utilities;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import seleniumProjectBase.BaseClass;


/**
 * This class is used to capture screenshot on soft assert failure and add it to reports
 *
 * @author Balaji N
 * @Description: This Class overide the onAssertFailure method of Assert class to customize take screenshot on the assert failure
 */
public class CustomSoftAssert extends SoftAssert {
    BaseClass base = new BaseClass();

    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        // Capture screenshot immediately on soft assert failure
        String screenshotPath = BaseClass.captureScreenshotBase64();
        ExtentReportManager.getTest().fail("Assertion failed: " + assertCommand.getMessage())
                .addScreenCaptureFromBase64String(screenshotPath, "Test Step failed");

        byte[] screenshot = ((TakesScreenshot) BaseClass.getDriver()).getScreenshotAs(OutputType.BYTES);
        Allure.getLifecycle().addAttachment("Screenshot on Failure", "image/png", "png", screenshot);
    }
}

