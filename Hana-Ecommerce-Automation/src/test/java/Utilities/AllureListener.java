package Utilities;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import seleniumProjectBase.BaseClass;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class AllureListener extends BaseClass implements ITestListener, IExecutionListener {
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) BaseClass.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseClass) testClass).getDriver();
        if (driver != null) {
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(saveScreenshotPNG(driver)));
        }
        Allure.step("Test failed: " + getTestMethodName(result));
        //  Allure.step("Error message: " + result.getThrowable().getMessage());
    }

    @Override
    public void onStart(ITestContext context) {
        clearAllureResultsAndReports();
    }

    @Override
    public void onFinish(ITestContext context) {
        //  clearAllureResultsAndReports();
        generateAndOpenAllureReport();
    }

    public void clearAllureResultsAndReports() {
        // Get the project directory
        String projectDir = System.getProperty("user.dir") + "\\reports\\";

        // Paths to the Allure results and report directories
        String allureResultsDir = projectDir + "\\allure-results";
        String allureReportDir = projectDir + "\\allure-report";

        // Delete existing Allure results and report directories
        deleteDirectory(new File(allureResultsDir));
        deleteDirectory(new File(allureReportDir));

        // Recreate the allure-results directory for the next test execution
        new File(allureResultsDir).mkdirs();
    }

    public void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
    }

    public void generateAndOpenAllureReport() {
        // Get the project directory
        String projectDir = System.getProperty("user.dir") + "\\reports\\";
        String allureResultsDir = projectDir + "\\allure-results";
        String allureReportDir = projectDir + "\\allure-report";

        try {
            // Generate Allure Report using Allure CLI
            // Command: allure generate <allure-results> -o <allure-report>
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c",
                    "allure", "generate", allureResultsDir, "-o", allureReportDir);
            processBuilder.inheritIO(); // Display the output in the console
            Process process = processBuilder.start();
            process.waitFor(); // Wait for the process to finish

            // Path to the generated Allure report's index.html
           /* File allureReportIndex = new File(allureReportDir + "\\index.html");

            // Automatically open the report in the default browser
            if (allureReportIndex.exists()) {
                Desktop.getDesktop().browse(allureReportIndex.toURI());
            } else {
                System.out.println("Allure report index.html not found at: " + allureReportIndex.getAbsolutePath());
            }*/
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}

