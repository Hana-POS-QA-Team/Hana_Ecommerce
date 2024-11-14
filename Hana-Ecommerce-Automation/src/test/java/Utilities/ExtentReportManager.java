package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import seleniumProjectBase.BaseClass;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ExtentReportManager extends BaseClass implements ITestListener {
    private static ExtentSparkReporter sparkReporter;
    private static ExtentReports extent;
    private static String repName;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    private String timeStamp;

    public void onStart(ITestContext testContext) {
        timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Hana-Test-AutomationReport-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\extentreport\\" + repName);
        // sparkReporter = new ExtentSparkReporter(".\\reports\\extentreport\\Hanapos_AutomationReport.html");
        sparkReporter.config().setDocumentTitle("Hana POS Automation");
        sparkReporter.config().setReportName("Hana POS Automation Test Report");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Hana - POS");
        extent.setSystemInfo("Tester Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", prop.getProperty("env"));

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        ExtentTest test = extentTest.get();
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    public static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) BaseClass.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public void onTestFailure(ITestResult result) {
        ExtentTest test = extentTest.get();
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        String logFilePath = System.getProperty("user.dir") + "\\logs\\" + result.getName() + "_" + timeStamp
                + "-console.log";
        //LogUtil.saveBrowserLogs(getDriver(), logFilePath);
    }

    public void onTestSkipped(ITestResult result) {
        ExtentTest test = extentTest.get();
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();

    }

    public static ExtentTest getTest() {
        ExtentTest test = extentTest.get();
        return test;
    }

    public static ExtentTest createTest(String testName, String description) {
        if (extent == null) {
            throw new IllegalStateException("ExtentReports is not initialized. Please call setupExtentReport() before creating tests.");
        }
        return extent.createTest(testName, description);
    }

    // User defined method for sending email..
    private final static String host = "imap.gmail.com";
    private final static String username = "hanaposqateam@gmail.com";
    // password is app password created
    private final static String password = "bcfburrmktksjckr";
    //User defined method for sending email..

    /*
     * public void sendEmail(String senderEmail,String senderPassword,String
     * recipientEmail) { // SMTP server properties Properties properties = new
     * Properties(); properties.put("mail.smtp.auth", "true");
     * properties.put("mail.smtp.starttls.enable", "true");
     * properties.put("mail.smtp.host", "smtp.gmail.com");
     * properties.put("mail.smtp.port", "587");
     *
     * // Create a Session object Session session = Session.getInstance(properties,
     * new Authenticator() { protected PasswordAuthentication
     * getPasswordAuthentication() { return new PasswordAuthentication(senderEmail,
     * senderPassword); } });
     *
     * try { // Create a MimeMessage object Message message = new
     * MimeMessage(session);
     *
     * // Set the sender and recipient addresses message.setFrom(new
     * InternetAddress(senderEmail)); message.setRecipient(Message.RecipientType.TO,
     * new InternetAddress(recipientEmail));
     *
     * // Set the subject message.setSubject("Test Report with attachment");
     *
     * // Create a MimeMultipart object Multipart multipart = new MimeMultipart();
     *
     * // Attach the file String filePath = ".\\reports\\Allure-Results"; //String
     * fileName = repName;
     *
     * MimeBodyPart attachmentPart = new MimeBodyPart();
     * attachmentPart.attachFile(filePath);
     * attachmentPart.setFileName("Allure-Results");
     *
     * // Create a MimeBodyPart for the text content MimeBodyPart textPart = new
     * MimeBodyPart(); textPart.
     * setText("Hi Team,\nPlease find the attached allure report for your reference"
     * );
     *
     * // Add the parts to the multipart multipart.addBodyPart(textPart);
     * multipart.addBodyPart(attachmentPart);
     *
     * // Set the content of the message message.setContent(multipart);
     *
     * // Send the message Transport.send(message);
     *
     * System.out.println("Allure report into Email sent successfully!");
     *
     * } catch (Exception e) { e.printStackTrace();
     * System.out.println("Email is not sent !!!"); } }
     */

    public void sendEmail(String senderEmail, String senderPassword, String recipientEmail) {
        // SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Create a Session object
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Zip the Allure Results folder
            String sourceFolderPath = ".\\reports\\Allure-Results";
            String zipFilePath = ".\\reports\\Allure-Results.zip";
            zipFolder(sourceFolderPath, zipFilePath);

            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

            // Set the subject
            message.setSubject("Test Report with attachment");

            // Create a MimeMultipart object
            Multipart multipart = new MimeMultipart();

            // Attach the zip file
            MimeBodyPart attachmentPart = new MimeBodyPart();
            FileDataSource source = new FileDataSource(zipFilePath);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName("Allure-Results.zip");

            // Create a MimeBodyPart for the text content
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Please find the attached allure report for your reference");

            // Add the parts to the multipart
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            // Set the content of the message
            message.setContent(multipart);

            // Send the message
            Transport.send(message);

            System.out.println("Allure report email sent successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Email was not sent.");
        }
    }

    // Method to zip a folder
    public static void zipFolder(String sourceFolderPath, String zipFilePath) throws Exception {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
            File folder = new File(sourceFolderPath);
            for (File file : folder.listFiles()) {
                try (FileOutputStream fos = new FileOutputStream(zipFilePath)) {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                    fos.write(file.getName().getBytes());
                    zipOutputStream.closeEntry();
                }
            }
        }
    }
}

	 
	 

