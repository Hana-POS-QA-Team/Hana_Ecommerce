package seleniumProjectBase;

import Utilities.SafeRobot;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import javax.imageio.ImageIO;

public class BaseClass implements FrameworkDesign {
    public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    //public static RemoteWebDriver driver;
    public static Properties prop;
    public static Logger logger;
    static String downloadPath = System.getProperty("user.dir");

    public static WebDriver initilizeBrowser() throws IOException {
        if (getProperties().getProperty("Execution_env").equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            //To get os type
            if (getProperties().getProperty("os").equalsIgnoreCase("windows")) {
                capabilities.setPlatform(Platform.WIN11);
            } else if (getProperties().getProperty("os").equalsIgnoreCase("mac")) {
                capabilities.setPlatform(Platform.MAC);
            } else {
                System.out.println("No matching OS..");
            }
            //browser
            switch (getProperties().getProperty("browser").toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                default:
                    System.out.println("No matching browser");
            }


            //    driver = new RemoteWebDriver(new URL(""), capabilities);

        } else if (getProperties().getProperty("Execution_env").equalsIgnoreCase("local")) {
            switch (getProperties().getProperty("browser").toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().timeout(30).setup();
                    ChromeOptions opt = new ChromeOptions();
                    HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                    chromePrefs.put("profile.default_content_settings.popups", 0);
                    chromePrefs.put("download.default_directory", downloadPath);
                    opt.setExperimentalOption("prefs", chromePrefs);
                    opt.addArguments("force-device-scale-factor=1.25"); // 125% zoom .80 as 80%
                    opt.addArguments("--incognito");
                    opt.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                    driver.set(new ChromeDriver(opt));
                    break;
                case "edge":
                    WebDriverManager.edgedriver().timeout(60).setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                    //  opt.merge(capabilities);
                    //  opt.addArguments("--remote-allow-origins=*");
                    //  capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                    driver.set(new EdgeDriver(edgeOptions));
                    break;
                default:
                    System.out.println("No matching browser");
                    driver = null;
            }
        }
        return driver.get();

    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static Properties getProperties() throws IOException {
        FileReader file = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\Config.properties");
        prop = new Properties();
        prop.load(file);
        return prop;
    }


    public static Logger getLogger() {
        logger = LogManager.getLogger(); //Log4j
        return logger;
    }

    @Override
    public void launchApplication(String browserName) {

    }

    @Override
    public void loadConfig() {

    }

    @Override
    public void HighlightElement(WebElement ele) {
        fluentWait(ele);
        JavascriptExecutor JS = (JavascriptExecutor) getDriver();
        JS.executeScript("arguments[0].scrollIntoView();", ele);
        JS.executeScript("arguments[0].style.border='3px solid red'", ele);
    }

    public String generaterandomeNumber(int i) {
        String generatedString = RandomStringUtils.randomNumeric(i);
        return generatedString;
    }

    public String twodigitrandomeString() {
        String generatedString = RandomStringUtils.randomAlphabetic(2);
        return generatedString;
    }

    public BigDecimal round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd;
    }

    @Override
    public void click(WebElement ele) {
        try {
            HighlightElement(ele);
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            ele.click();
            //	ext.reportStep("Click Successful", "PASS");
        } catch (Exception e) {
            //ext.reportStep("Click Failed", "FAIL");
        }
    }

    @Override
    public void jsScrollClick(WebElement ele) {
        try {
            fluentWait(ele);
            HighlightElement(ele);
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].click();", ele);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionClick(WebElement ele) {
        try {
            HighlightElement(ele);
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            Actions action = new Actions(getDriver());
            action.moveToElement(ele).click().build().perform();
        } catch (StaleElementReferenceException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Override
    public void actionScrollClick(WebElement ele) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOf(ele));
            WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(ele);
            Actions action = new Actions(getDriver());
            action.scrollFromOrigin(scrollOrigin, 100, 200)
                    .perform();
            HighlightElement(ele);
            actionClick(ele);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void jsClick(WebElement ele) {
        try {
            HighlightElement(ele);
            fluentWait(ele);
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].click();", ele);
        } catch (Exception e) {
            Assert.fail("Unable to click the element using js click method");
            throw new RuntimeException();
        }
    }

    @Override
    public void append(WebElement ele, String data) {
        try {
            HighlightElement(ele);
            ele.sendKeys(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionClear(WebElement ele) {
        HighlightElement(ele);
        try {
            Actions action = new Actions(getDriver());
            action.click();
            action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE);
            action.perform();
            String currentValue = ele.getAttribute("value");
            System.out.println("Verify Current value after clearing: " + currentValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DoubleClick(WebElement ele) {
        Actions actions = new Actions(getDriver());
        fluentWait(ele);
        actions.doubleClick(ele).build().perform();
    }

    public void DoubleClickAndType(WebElement ele, String data) {
        ele.clear();
        HighlightElement(ele);
        Actions actions = new Actions(getDriver());
        explicitWait(ele);
        actions.doubleClick(ele).build().perform();
        ele.sendKeys(data);
    }

    @Override
    public void clickAndType(WebElement ele, String data) {
        try {
            ele.clear();
            HighlightElement(ele);
            ele.click();
            ele.sendKeys(data);
        } catch (StaleElementReferenceException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void actionType(WebElement ele, String data) {
        try {
            ele.clear();
            HighlightElement(ele);
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            Actions action = new Actions(getDriver());
            action.moveToElement(ele).sendKeys(data).build().perform();
        } catch (StaleElementReferenceException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void clearAndType(WebElement ele, String data) {
        try {
            ele.clear();
            HighlightElement(ele);
            ele.sendKeys(data);
        } catch (ElementNotInteractableException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException();
        }
    }

    public void jsClear(WebElement ele) {
        HighlightElement(ele);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("arguments[0].value='';", ele);
    }

    @Override
    public void jsClearAndType(WebElement ele, String data) {
        try {
            HighlightElement(ele);
            JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
            jsExecutor.executeScript("arguments[0].value='';", ele);
            jsExecutor.executeScript("arguments[0].value=arguments[1];", ele, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void jsTypeAndEnter(WebElement ele, String data) {
        try {
            HighlightElement(ele);
            ele.clear();
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].value='" + data + "'", ele);
            ele.sendKeys(Keys.ENTER);
        } catch (ElementNotInteractableException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public boolean IsDisabled(WebElement ele) {
        HighlightElement(ele);
        String disabledAttribute = ele.getAttribute("disabled");
        boolean isDisabled = disabledAttribute != null && disabledAttribute.equals("true");
        return isDisabled;
    }

    @Override
    public void jsScrollClickAndEnter(WebElement ele, String data) {
        try {
            HighlightElement(ele);
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].scrollIntoView();", ele);
            ele.clear();
            clickAndType(ele, data);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void ThreadWait(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delayWithGivenTime(int i) {
        try {
            ThreadWait(i);


        } catch (Exception e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    /**
     * Fluent wait on specific element appears with given time
     *
     * @param ele - Specific element to be interacted
     * @param i   - Provided Timeout seconds to be entered
     * @param j   - Provided pooling every given timeout to be entered
     * @Description: This function uses the fluent wait selenium function for particular specific element will appears to interact with given time
     * @Author Balaji N
     */
    public void delayWithGivenTime(WebElement ele, int i, int j) {
        try {
            fluentWait(ele, i, j);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public void RobotPressEnter() {
        try {
            Actions action = new Actions(getDriver());
            action.sendKeys(Keys.ENTER).build().perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ActionPressEnter() {
        try {
            Actions action = new Actions(getDriver());
            action.sendKeys(Keys.ENTER).build().perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void PressEscapeKey() {
        try {
            Actions action = new Actions(getDriver());
            action.sendKeys(Keys.ESCAPE).build().perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ActionArrowDown() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Title: actionsEscapeKey - Press the escape key
     * @Description: This method is used to press the escape key using action class
     * @Author Balaji N
     */
    public void actionsEscapeKey() {
        try {
            delayWithGivenTime(1000);
            Actions actions = new Actions(getDriver());
            actions.sendKeys(Keys.ESCAPE).perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void RobotArrowDownAndEnter() {
        try {
            SafeRobot robot = SafeRobot.getInstance();
            synchronized (robot) {
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);

                // Press enter key
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            }
        } catch (AWTException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void dropDown(WebElement ele, String value, String usingmethod) {
        Select select = new Select(ele);

        try {
            //	HighlightElement(ele);
            switch (usingmethod) {
                case "index":
                    select.selectByIndex(Integer.parseInt(value));
                    break;
                case "value":
                    select.selectByValue(value);
                    break;
                case "VisibleText":
                    jsClick(ele);
                    select.selectByVisibleText(value);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    public void SelectDropDownVisibleText(WebElement ele, String value) {
        Select select = new Select(ele);
        select.selectByVisibleText(value);
    }

    @Override
    public void switchToAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            getDriver().switchTo().alert();
            getAlertText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void acceptAlert() {
        String text = "";
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            getDriver().switchTo().activeElement();
            Alert alert = getDriver().switchTo().alert();
            text = alert.getText();
            alert.accept();
        } catch (NoAlertPresentException e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("window.confirm = function(msg) { return true; }");
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : " + e.getMessage());
        }
        System.out.println("Displayed alert text is : " + text);
    }

    public void PressF8() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("var event = new KeyboardEvent('keydown', {keyCode: 119, which: 119}); document.dispatchEvent(event);");

        // Robot class does not work on parallel execution.....
        /*
         * try { SafeRobot robot = SafeRobot.getInstance(); // Press Tab key to focus on
         * the "F8" to navigate gift card tab synchronized (robot) {
         * robot.keyPress(KeyEvent.VK_F8); robot.keyRelease(KeyEvent.VK_F8); }} catch
         * (AWTException e) { e.printStackTrace(); }
         */
    }

    public void action_PressF8() {
        Actions actions = new Actions(getDriver());
        actions.sendKeys(Keys.F8).perform();
    }

    public void BackButton() {

        try {
            SafeRobot robot = SafeRobot.getInstance();
            // Press Tab key to focus on the "left arrow" button
            synchronized (robot) {
                robot.keyPress(KeyEvent.VK_ALT);
                robot.keyPress(KeyEvent.VK_B);
                robot.keyRelease(KeyEvent.VK_B);
                robot.keyRelease(KeyEvent.VK_ALT);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }


    public void JsAcceptAlert() {
        getDriver().switchTo().activeElement();
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.confirm = function(msg) { return true; }");
    }

    public void JsDismissAlert() {
        getDriver().switchTo().activeElement();
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.confirm = function(msg) { return false; }");
    }

    @Override
    public void dismissAlert() {
        String text = "";
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            getDriver().switchTo().activeElement();
            Alert alert = getDriver().switchTo().alert();
            text = alert.getText();
            alert.dismiss();

        } catch (NoAlertPresentException e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("window.confirm = function(msg) { return false; }");
            //	RobotDismissAlert();
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("WebDriverException : " + e.getMessage());
        }
        System.out.println("Displayed alert text is : " + text);
    }

    @Override
    public String getAlertText() {
        String text = "";
        try {
            getDriver().switchTo().activeElement();
            Alert alert = getDriver().switchTo().alert();
            text = alert.getText();
        } catch (NoAlertPresentException e) {
            System.out.println("There is no alert present.");
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : " + e.getMessage());
        }
        return text;
    }

    public void RobotDismissAlert() {
        try {
            SafeRobot safeRobot = SafeRobot.getInstance();
            synchronized (SafeRobot.class) {
                safeRobot.delay(1000); // Initial delay
                safeRobot.keyPress(KeyEvent.VK_ESCAPE);
                safeRobot.keyRelease(KeyEvent.VK_ESCAPE);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    public void ActionDismissAlert() {
        delayWithGivenTime(1000);
        Actions actions = new Actions(getDriver());
        actions.sendKeys(Keys.ESCAPE).perform();
    }


    public void ActionAcceptAlert() {
        delayWithGivenTime(1000);
        Actions actions = new Actions(getDriver());
        actions.sendKeys(Keys.ARROW_LEFT).perform();
        delayWithGivenTime(1000);
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void RobotAcceptAlert() {
        try {
            SafeRobot safeRobot = SafeRobot.getInstance();
            synchronized (SafeRobot.class) {
                safeRobot.delay(1000); // Initial delay
                safeRobot.keyPress(KeyEvent.VK_LEFT);
                safeRobot.keyRelease(KeyEvent.VK_LEFT);
                safeRobot.delay(1000); // Delay between key presses
                safeRobot.keyPress(KeyEvent.VK_ENTER);
                safeRobot.keyRelease(KeyEvent.VK_ENTER);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }


    public void RobotAccept_LeaveKey_Alert() {

        try {
            SafeRobot robot = SafeRobot.getInstance();
            synchronized (robot) {
                delayWithGivenTime(1000);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void ConsoleLog(String message) {
        System.out.println(message);
    }

    @Override
    public void explicitWait(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void explicitWait(WebElement ele, int i) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(i));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    @Override
    public void fluentWait(WebElement ele) {
        Wait<WebDriver> wait = null;
        try {
            wait = new FluentWait<WebDriver>((WebDriver) getDriver())
                    .withTimeout(Duration.ofSeconds(50))
                    .pollingEvery(Duration.ofSeconds(4))
                    .ignoring(Exception.class);
            wait.until(ExpectedConditions.visibilityOf(ele));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void fluentWait(WebElement ele, int i, int j) {
        Wait<WebDriver> wait = null;
        try {
            wait = new FluentWait<WebDriver>((WebDriver) getDriver())
                    .withTimeout(Duration.ofSeconds(i))
                    .pollingEvery(Duration.ofSeconds(j))
                    .ignoring(Exception.class);
            wait.until(ExpectedConditions.visibilityOf(ele));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void switchToFrame(int index) {
        try {
            getDriver().switchTo().frame(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void switchToFrame(WebElement ele) {
        try {
            getDriver().switchTo().frame(ele);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void switchToFrame(String idOrName) {
        try {
            getDriver().switchTo().frame(idOrName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void defaultContent() {
        try {
            getDriver().switchTo().defaultContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean verifyUrl(String url) {
        if (getDriver().getCurrentUrl().equals(url)) {
            System.out.println("The url: " + url + " matched successfully");
            return true;
        } else {
            System.out.println("The url: " + url + " not matched");
        }
        return false;
    }

    @Override
    public boolean verifyTitle(String title) {
        if (getDriver().getTitle().equals(title)) {
            System.out.println("Page title: " + title + " matched successfully");
            return true;
        } else {
            System.out.println("Page url: " + title + " not matched");
        }
        return false;
    }

    @Override
    public String getElementText(WebElement ele) {
        String text = "";
        try {
            HighlightElement(ele);
            text = ele.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    @Override
    public String getTypedText(WebElement ele) {
        String attributeValue = ele.getAttribute("value");
        return attributeValue;
    }

    @Override
    public void searchAndSelect(java.util.List<WebElement> ele, String data) {
        try {
            java.util.List<WebElement> l1 = ele;
            for (WebElement val : l1) {
                if (val.getText().equals(data)) {
                    actionClick(val);
                    break;
                }
            }

        } catch (ElementNotInteractableException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void drawSignature(WebElement ele) {
        try {
            Actions actionBuilder = new Actions(getDriver());
            Action drawOnCanvas = actionBuilder
                    .moveToElement(ele, 8, 8)
                    .clickAndHold(ele)
                    .moveByOffset(120, 120)
                    .moveByOffset(60, 70)
                    .moveByOffset(-140, -140)
                    .release(ele)
                    .build();
            drawOnCanvas.perform();
        } catch (StaleElementReferenceException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public String Eastern_TimeZone() {
        LocalDateTime systemDateTime = LocalDateTime.now();

        ZoneId systemZone = ZoneId.systemDefault();
        ZonedDateTime systemZonedDateTime = systemDateTime.atZone(systemZone);

        // Define the Eastern Time (US & Canada) zone
        ZoneId easternTimeZone = ZoneId.of("America/New_York");  // Eastern Time Zone

        ZonedDateTime easternZonedDateTime = systemZonedDateTime.withZoneSameInstant(easternTimeZone);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        String formattedEasternTime = easternZonedDateTime.format(formatter).toUpperCase();
        ;

        return formattedEasternTime;
    }

    public String Atlantic_TimeZone() {
        // Get the current system time
        LocalDateTime systemDateTime = LocalDateTime.now();

        // Get the system's default zone
        ZoneId systemZone = ZoneId.systemDefault();
        ZonedDateTime systemZonedDateTime = systemDateTime.atZone(systemZone);

        // Define the Atlantic Standard Time zone (UTC-04:00)
        ZoneId atlanticTimeZone = ZoneId.of("America/Halifax");

        // Convert to Atlantic Standard Time
        ZonedDateTime atlanticZonedDateTime = systemZonedDateTime.withZoneSameInstant(atlanticTimeZone);

        // Format the Atlantic Time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        String formattedAtlanticTime = atlanticZonedDateTime.format(formatter).toUpperCase();

        // Replace "SEPT" with "SEP" if necessary
        if (formattedAtlanticTime.startsWith("SEPT")) {
            formattedAtlanticTime = formattedAtlanticTime.replaceFirst("SEPT", "SEP");
        }
        return formattedAtlanticTime; // Return the formatted Atlantic Time
    }


    public String Atlantic_TimeZone_NumberDateFormat() {
        LocalDateTime systemDateTime = LocalDateTime.now();

        ZoneId systemZone = ZoneId.systemDefault();
        ZonedDateTime systemZonedDateTime = systemDateTime.atZone(systemZone);

        // Define the Atlantic Standard Time zone (UTC-04:00)
        ZoneId atlanticTimeZone = ZoneId.of("America/Halifax"); // Use "America/Halifax" for AST

        ZonedDateTime atlanticZonedDateTime = systemZonedDateTime.withZoneSameInstant(atlanticTimeZone);

        // Remove the space at mm a to get hh:mma = 05:30AM || h:mma = 5:30AM
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mma");
        String formattedAtlanticTime = atlanticZonedDateTime.format(formatter).toUpperCase();

        return formattedAtlanticTime;
    }

    public String Central_TimeZone() {
        LocalDateTime systemDateTime = LocalDateTime.now();

        ZoneId systemZone = ZoneId.systemDefault();
        ZonedDateTime systemZonedDateTime = systemDateTime.atZone(systemZone);

        // Define the Central Time (US & Canada) zone
        ZoneId centralTimeZone = ZoneId.of("America/Chicago");  // Central Time Zone (UTC-06:00)

        // Convert system date-time to Central Time
        ZonedDateTime centralZonedDateTime = systemZonedDateTime.withZoneSameInstant(centralTimeZone);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        String formattedCentralTime = centralZonedDateTime.format(formatter).toUpperCase();
        ;

        return formattedCentralTime;
    }

    public String Mountain_TimeZone() {
        LocalDateTime systemDateTime = LocalDateTime.now();

        ZoneId systemZone = ZoneId.systemDefault();
        ZonedDateTime systemZonedDateTime = systemDateTime.atZone(systemZone);

        // Define the Mountain Time (US & Canada) zone
        ZoneId mountainTimeZone = ZoneId.of("America/Denver");  // Mountain Time Zone (UTC-07:00)

        ZonedDateTime mountainZonedDateTime = systemZonedDateTime.withZoneSameInstant(mountainTimeZone);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        String formattedMountainTime = mountainZonedDateTime.format(formatter).toUpperCase();
        ;

        return formattedMountainTime;
    }

    public String Pacific_TimeZone() {
        LocalDateTime systemDateTime = LocalDateTime.now();

        ZoneId systemZone = ZoneId.systemDefault();
        ZonedDateTime systemZonedDateTime = systemDateTime.atZone(systemZone);

        // Define the Pacific Time (US & Canada) zone (UTC-08:00)
        ZoneId pacificTimeZone = ZoneId.of("America/Los_Angeles");

        ZonedDateTime pacificZonedDateTime = systemZonedDateTime.withZoneSameInstant(pacificTimeZone);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        String formattedPacificTime = pacificZonedDateTime.format(formatter).toUpperCase();
        ;

        return formattedPacificTime;
    }

    public String Alaska_TimeZone() {
        LocalDateTime systemDateTime = LocalDateTime.now();
        // Remove the space at mm a to get h:mma = 5:30AM
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

        ZoneId systemZone = ZoneId.systemDefault();
        ZonedDateTime systemZonedDateTime = systemDateTime.atZone(systemZone);

        // Define the Alaska Time zone (America/Anchorage)
        ZoneId alaskaTimeZone = ZoneId.of("America/Anchorage");  // Alaska Time (UTC-09:00)

        ZonedDateTime alaskaZonedDateTime = systemZonedDateTime.withZoneSameInstant(alaskaTimeZone);

        String formattedAlaskaTime = alaskaZonedDateTime.format(inputFormatter).toUpperCase();
        ;

        return formattedAlaskaTime;
    }

    public String CurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedCurrentDate = currentDate.format(formatter);
        return (formattedCurrentDate);
    }

    public String PreviousDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate previousDay = currentDate.plusDays(-1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedPreviousDay = previousDay.format(formatter);
        return formattedPreviousDay;
    }

    public String NextDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate previousDay = currentDate.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedPreviousDay = previousDay.format(formatter);
        return formattedPreviousDay;
    }

    public String Next_20Days_Date() {
        LocalDate currentDate = LocalDate.now();
        LocalDate previousDay = currentDate.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedPreviousDay = previousDay.format(formatter);
        return formattedPreviousDay;
    }

    public String Next_TwoYears_Date() {
        LocalDate currentDate = LocalDate.now();
        LocalDate previousDay = currentDate.plusDays(730);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedPreviousDay = previousDay.format(formatter);
        return formattedPreviousDay;
    }

    @Override
    public void switchToWindowbyIndex(int i) {
        try {
            Set<String> windowIds = getDriver().getWindowHandles();
            java.util.List<String> windowIdslist = new ArrayList<>(windowIds);
            String childWindowId = windowIdslist.get(i);
            getDriver().switchTo().window(childWindowId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBackgroundColor(WebElement ele) {
        String cssValue = ele.getCssValue("color");
        return cssValue;
    }

    public static void GetBackgroundColor(WebElement ele) {
        String colorele = ele.getCssValue("color");
        String hex_code = Color.fromString(colorele).asHex();
        System.out.println("Displayed heading color is : " + colorele);
        System.out.println("Displayed heading hex color code is : " + hex_code);
    }

    @Override
    public void jsDatePicker(WebElement ele, String dateval) {
        try {
            HighlightElement(ele);
            JavascriptExecutor JS = (JavascriptExecutor) getDriver();
            JS.executeScript("arguments[0].setAttribute('value','" + dateval + "');", ele);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void datePicker(WebElement ele, WebElement ActualMonthYear, WebElement NextArrow, java.util.List<WebElement> alldates, String date) {
        String day = date.substring(4, 6);
        String monthyear = date.substring(0, 3) + ", " + date.substring(7, 11);
        HighlightElement(ele);
        click(ele);
        explicitWait(ele);

        while (true) {
            if (ActualMonthYear.equals(monthyear)) {
                break;
            }
            click(NextArrow);
        }

        for (WebElement element : alldates) {
            String days = element.getText();
            if (days.equals(day)) {
                click(element);
                break;
            }
        }

    }

    @Override
    public void MouseHover(WebElement ele) {
        try {
            HighlightElement(ele);
            Actions action = new Actions(getDriver());
            action.moveToElement(ele).build().perform();
            delayWithGivenTime(500);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public String HandleTooltip(WebElement ele) {
        try {
            HighlightElement(ele);
            Actions action = new Actions(getDriver());
            explicitWait(ele);
            action.moveToElement(ele).build().perform();
            delayWithGivenTime(1500);
            String title = ele.getAttribute("title");
            return title;
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean mouseHoverByJavaScript(WebElement ele) {
        try {
            String javaScript = "var evObj = document.createEvent('MouseEvents');"
                    + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                    + "arguments[0].dispatchEvent(evObj);";
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript(javaScript, ele);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public void MouseHoverAndClick(WebElement hoverele, WebElement clickeele) {
        try {
            HighlightElement(hoverele);
            fluentWait(clickeele);

            Actions action = new Actions(getDriver());
            action.moveToElement(hoverele).build().perform();

            delayWithGivenTime(2000);

            HighlightElement(clickeele);
            action.moveToElement(clickeele).click().build().perform();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Override
    public String captureScreen(String tname) {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        //System.getProperty("user.dir") +
        String targetDir = ".\\screenshots\\";
        String targetFilePath = targetDir + tname + "_" + timeStamp + ".png";

        try {
            Files.createDirectories(Paths.get(targetDir));
            Files.copy(sourceFile.toPath(), Paths.get(targetFilePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return targetFilePath;
    }

    public String captureScreenshot(String screenshotName) {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String dest = System.getProperty("user.dir") + "/reports/screenshots/" + screenshotName + "_" + timeStamp + ".png";
        //  String dest = ".\\reports\\screenshots\\" + screenshotName  + "_" + timeStamp + ".png";
        try {
            Robot robot = new Robot();
            java.awt.Rectangle screenRect = new java.awt.Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            File screenshotFile = new File(dest);
            ImageIO.write(screenFullImage, "png", screenshotFile);
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
        return dest;
    }


    public static String captureScreenshotBase64() {
        String screenshotBase64 = "";
        try {
            // Capture the screenshot using Robot class
            Robot robot = new Robot();
            java.awt.Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

            // Convert the captured image to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(screenFullImage, "png", baos);
            byte[] screenshotBytes = baos.toByteArray();

            // Encode the byte array to Base64
            screenshotBase64 = Base64.getEncoder().encodeToString(screenshotBytes);
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }

        return screenshotBase64; // Return Base64 string of the screenshot
    }

    @Override
    public void uploadFile(WebElement ele, String filepath) {
        try {
            HighlightElement(ele);
            ele.click();
            delayWithGivenTime(2000);
            Robot rb = new Robot();

            StringSelection str = new StringSelection(filepath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_V);
            rb.keyRelease(KeyEvent.VK_CONTROL);
            rb.keyRelease(KeyEvent.VK_V);
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);
        } catch (HeadlessException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    public void sendImageFile(String querySelector, String imageName) throws InterruptedException {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) getDriver();
            WebElement chooseImage = (WebElement) jse.executeScript(" return " + querySelector + "");
            File file = new File("./testFiles/" + imageName + ".png");
            chooseImage.sendKeys(file.getAbsolutePath());
        } catch (StaleElementReferenceException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    @Override
    public void VerifyFileDownLoad(WebElement ele, String pathfile, String filename) {
        try {
            click(ele);
            File filelocation = new File(pathfile);
            File[] totalfiles = filelocation.listFiles();

            for (File file : totalfiles) {
                if (file.getName().equals(filename)) {
                    System.out.println("File is download successfully");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void HandleAnalogClock(String TimeValue, WebElement ele) {
        String DeliveryTime = TimeValue; //5:30 PM
        click(ele);
        delayWithGivenTime(2000);
        getDriver().switchTo().activeElement();

        String[] dt = DeliveryTime.split(":");
        System.out.println("Hour is :" + dt[0]);
        System.out.println("Minutes is :" + dt[1]);

        WebElement SelectHour = getDriver().findElement(By.xpath("//div[normalize-space()= " + dt[0] + ")]"));
        delayWithGivenTime(2000);
        click(SelectHour);

        WebElement SelectMinute = getDriver().findElement(By.xpath("//div[normalize-space()=" + dt[1].substring(0, 1) + ")]"));
        click(SelectMinute);

        WebElement PMbutton = getDriver().findElement(By.xpath("(//a[normalize-space()='PM'])[1]"));
        jsScrollClick(PMbutton);

    }

    @Override
    public String GetCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        return dtf.format(now);
    }

    @Override
    public boolean verifyIsDisplayed(WebElement ele) {
        try {
            HighlightElement(ele);
            if (ele.isDisplayed()) {
                return true;
            }
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean verifyIsEnabled(WebElement ele) {
        try {
            HighlightElement(ele);
            if (ele.isEnabled()) {
                return true;
            }
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : " + e.getMessage());
        }
        return false;

    }

    @Override
    public boolean verifyIsSelected(WebElement ele) {
        try {
            HighlightElement(ele);
            if (ele.isSelected()) {
                return true;
            }
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : " + e.getMessage());
        }
        return false;
    }

    @Override
    public void rightClickAction() {
        Actions action = new Actions(getDriver());
        action.contextClick().build().perform();

    }

    @Override
    public void dragAndDrop(WebElement source, WebElement target) {
        Actions action = new Actions(getDriver());
        action.dragAndDrop(source, target).build().perform();
    }

    @Override
    public int getColumncount(WebElement ele) {
        java.util.List<WebElement> columns = ele.findElements(By.tagName("td"));
        int a = columns.size();
        System.out.println(columns.size());
        for (WebElement column : columns) {
            System.out.print(column.getText());
            System.out.print("|");
        }
        return a;
    }

    @Override
    public int getRowCount(WebElement ele) {
        List<WebElement> rows = ele.findElements(By.tagName("tr"));
        int a = rows.size() - 1;
        return a;
    }

    @Override
    public void scrollAction(WebElement ele) {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].scrollIntoView(true);", ele);
    }

    @Override
    public void HandleAutocomplete(WebElement ele, String data) {
        try {
            HighlightElement(ele);
            ele.sendKeys(data);
            delayWithGivenTime(2000);
            fluentWait(ele);
            Actions actions = new Actions(getDriver());
            //	actions.moveToElement(hoverElement).moveToElement(clickElement).click().build().perform();
            actions.sendKeys(ele, Keys.DOWN).sendKeys(Keys.ENTER).build().perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String rgbaToHex(String color) {
        String[] numbers = color.replace("rgba(", "").replace("rgb(", "").replace(")", "").split(",");
        int r = Integer.parseInt(numbers[0].trim());
        int g = Integer.parseInt(numbers[1].trim());
        int b = Integer.parseInt(numbers[2].trim());
        return String.format("#%02x%02x%02x", r, g, b);
    }

    /**
     * Press the Tab key
     *
     * @Description: This static method is used to press the Tab key
     * @Author Balaji N
     */
    public static void press_Tab_Key() {
        Actions actions = new Actions(getDriver());
        actions.sendKeys(Keys.TAB).build().perform();
    }


}
