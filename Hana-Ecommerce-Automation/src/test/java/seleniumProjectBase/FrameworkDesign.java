package seleniumProjectBase;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface FrameworkDesign {

    /**
     * @param browserName - This will load the specified browser to launch
     * @author Balaji N - Dream Corp
     * @Description - This method will launch the application
     */

    public void launchApplication(String browserName);

    /**
     * @author Balaji N - Dream Corp
     * @Description - This method will load the config properties file
     */

    public void loadConfig();

    /**
     * @author Balaji N - Dream Corp
     * @Description - This method will highlight the field of element
     */

    public void HighlightElement(WebElement ele);

    /**
     * This method will click the element
     *
     * @param ele - The Webelement (button/link/element) to be clicked
     * @throws StaleElementReferenceException
     * @author Balaji N - Dream Corp
     */

    public void click(WebElement ele);

    /**
     * This method will scroll and click the element using javascript executor
     *
     * @param ele - The Webelement (button/link/element) to be clicked
     * @throws StaleElementReferenceException
     * @author Balaji N - Dream Corp
     */

    public void jsScrollClick(WebElement ele);

    /**
     * This method will click the element using action class
     *
     * @param ele - The Webelement (button/link/element) to be clicked
     * @throws StaleElementReferenceException
     * @author Balaji N - Dream Corp
     */
    public void actionClick(WebElement ele);

    /**
     * This method will scroll and click the element using action class
     *
     * @param ele - The Webelement (button/link/element) to be clicked
     * @throws StaleElementReferenceException
     * @author Balaji N - Dream Corp
     */

    public void actionScrollClick(WebElement ele);

    /**
     * This method will click the element using javascript executor
     *
     * @param ele - The Webelement (button/link/element) to be clicked
     * @author Balaji N - Dream Corp
     */
    public void jsClick(WebElement ele);

    /**
     * This method will enter the value in the given text field
     *
     * @param ele  - The Webelement (text field) in which the data to be entered
     * @param data - The data to be sent to the webelement
     * @throws ElementNotInteractable,IllegalArgumentException(throws if keysToSend is null)
     * @author Balaji N - Dream Corp
     */

    public void append(WebElement ele, String data);

    /**
     * This method will clear the data in text field using action class
     *
     * @param ele - The Webelement (text field) in which the data to be entered
     * @author Balaji N - Dream Corp
     */
    public void actionClear(WebElement ele);

    /**
     * This method will click and enter the value in the given text field
     *
     * @param ele  - The Webelement (text field) in which the data to be entered
     * @param data - The data to be sent to the webelement
     * @author Balaji N - Dream Corp
     */
    public void clickAndType(WebElement ele, String data);

    /**
     * This method will enter the value in the given text field using action class
     *
     * @param ele  - The Webelement (text field) in which the data to be entered
     * @param data - The data to be sent to the webelement
     * @author Balaji N - Dream Corp
     */
    public void actionType(WebElement ele, String data);

    /**
     * This method will clear and type the value in the given text field
     *
     * @param ele  - The Webelement (text field) in which the data to be entered
     * @param data - The data to be sent to the webelement
     * @throws ElementNotInteractable,IllegalArgumentException(throws if keysToSend is null)
     * @author Balaji N - Dream Corp
     */
    public void clearAndType(WebElement ele, String data);

    /**
     * This method will clear and type the value in the given text field using javascript executor
     *
     * @param ele  - The Webelement (text field) in which the data to be entered
     * @param data - The data to be sent to the webelement
     * @throws ElementNotInteractable,IllegalArgumentException(throws if keysToSend is null)
     * @author Balaji N - Dream Corp
     */
    public void jsClearAndType(WebElement ele, String data);

    /**
     * This method will enter the value in the given text field using javascript executor
     *
     * @param ele  - The Webelement (text field) in which the data to be entered
     * @param data - The data to be sent to the webelement
     * @author Balaji N - Dream Corp
     */
    public void jsTypeAndEnter(WebElement ele, String data);

    /**
     * This method will scroll and enter the value in the given text field using javascript executor
     *
     * @param ele  - The Webelement (text field) in which the data to be entered
     * @param data - The data to be sent to the webelement
     * @author Balaji N - Dream Corp
     */
    public void jsScrollClickAndEnter(WebElement ele, String data);

    /**
     * This method will wait for the given time using robot class
     *
     * @param timeInterval - The int (time in milliseconds) to be waited
     * @author Balaji N - Dream Corp
     */

    public void delayWithGivenTime(int timeInterval);

    /**
     * This method will select the value in the given dropdown using select class
     *
     * @param ele         - This element will find the dropdown field
     * @param value       - The value to be sent to the webelement
     * @param usingmethod - The type of method to be used to select the value
     * @author Balaji N - Dream Corp
     */

    public void dropDown(WebElement ele, String value, String usingmethod);

    /**
     * This method will switch to the alert
     *
     * @throws NoAlertPresentException
     * @author Balaji N - Dream Corp
     */

    public void switchToAlert();

    /**
     * This method will switch to the alert and accept the alert
     *
     * @throws NoAlertPresentException
     * @author Balaji N - Dream Corp
     */

    public void acceptAlert();

    /**
     * This method will switch to the alert and dismiss the alert
     *
     * @throws NoAlertPresentException
     * @author Balaji N - Dream Corp
     */

    public void dismissAlert();

    /**
     * This method will switch to the alert and get the text
     *
     * @return - The text of the alert message
     * @throws NoAlertPresentException
     * @author Balaji N - Dream Corp
     */

    public String getAlertText();

    /**
     * This method will wait for the element to be visible explicitly
     *
     * @param ele - The Webelement to wait of presence
     * @throws ElementNotVisibleException
     * @author Balaji N - Dream Corp
     */

    public void explicitWait(WebElement ele);

    /**
     * This method will wait for the element to be visible using fluent wait
     *
     * @param ele - The Webelement to wait of presence
     * @throws ElementNotVisibleException
     * @author Balaji N - Dream Corp
     */

    public void fluentWait(WebElement ele);

    /**
     * This method will switch to the specific frame using index
     *
     * @param index - The int (frame) to be switched
     * @throws NoSuchFrameException
     * @author Balaji N - Dream Corp
     */
    public void switchToFrame(int index);

    /**
     * This method will switch to the specific frame
     *
     * @param ele - The Webelement (frame) to be switched
     * @throws NoSuchFrameException, StaleElementReferenceException
     * @author Balaji N - Dream Corp
     */
    public void switchToFrame(WebElement ele);

    /**
     * This method will switch to the specific frame using Id (or) Name
     *
     * @param idOrName - The String (frame) to be switched
     * @throws NoSuchFrameException
     * @author Balaji N - Dream Corp
     */
    public void switchToFrame(String idOrName);

    /**
     * This method will switch to the first frame on the page
     *
     * @return This driver focused on the top window/first frame.
     * @author Balaji N - Dream Corp
     */
    public void defaultContent();

    /**
     * This method will verify browser actual url with expected
     *
     * @param url - The url to be checked
     * @return true if the given object represents a String equivalent to this url, false otherwise
     * @author Balaji N - Dream Corp
     */
    public boolean verifyUrl(String url);

    /**
     * This method will verify browser actual title with expected
     *
     * @param title - The expected title of the browser
     * @return true if the given object represents a String equivalent to this title, false otherwise
     * @author Balaji N - Dream Corp
     */
    public boolean verifyTitle(String title);

    /**
     * This method will get the visible text of the element
     *
     * @param ele - The Webelement (button/link/element) in which text to be retrieved
     * @author Balaji N - Dream Corp
     */
    public String getElementText(WebElement ele);

    /**
     * This method will get the text of the element textbox
     *
     * @param ele - The Webelement (button/link/element) in which text to be retrieved
     * @return The attribute/property's current value (or) null if the value is not set.
     * @author Balaji N - Dream Corp
     */
    public String getTypedText(WebElement ele);

    /**
     * This method is used to search and select the element
     *
     * @param ele  - This is a list of web elements
     * @param data - This is the data to be searched
     * @author Balaji N - Dream Corp
     */
    public void searchAndSelect(List<WebElement> ele, String data);

    /**
     * This method is used to draw the signature on the element
     *
     * @param ele  - This is a list of web elements
     * @param data - This is the data to be searched
     * @author Balaji N - Dream Corp
     */
    public void drawSignature(WebElement ele);

    /**
     * This method will switch to the specific window based on index position, and which is start with zeroth position
     *
     * @param index - The provided index of the window to be switched
     * @author Balaji N - Dream Corp
     */
    public void switchToWindowbyIndex(int index);

    /**
     * This method will get the Color values of the element
     *
     * @param ele - The Webelement (button/link/element) in which text to be retrieved
     * @return The visible text of this element.
     * @author Balaji N - Dream Corp
     */
    public String getBackgroundColor(WebElement ele);

    /**
     * This method will handle date picker using javascript executor
     *
     * @param ele     - The date picker element to be interacted
     * @param dateval - The data to be searched
     * @author Balaji N - Dream Corp
     */

    public void jsDatePicker(WebElement ele, String dateval);

    /**
     * This method will handle date picker using javascript executor
     *
     * @param ele - The date picker element to be interacted
     * @author Balaji N - Dream Corp
     */

    public void datePicker(WebElement ele, WebElement ActualMonthYear, WebElement NextArrow, List<WebElement> alldates, String date);

    /**
     * This method will perform the scroll action using action class
     *
     * @param ele - The element to be scrolled
     * @author Balaji N - Dream Corp
     */

    public void scrollAction(WebElement ele);

    /**
     * This method will mouse hover the specific element using action class
     *
     * @param ele - The element to be hovered
     * @author Balaji N - Dream Corp
     */

    public void MouseHover(WebElement ele);

    /**
     * This method will mouse hover the specific element using javascript executor
     *
     * @param ele - The element to be hovered
     * @author Balaji N - Dream Corp
     */

    public boolean mouseHoverByJavaScript(WebElement ele);

    /**
     * This method will hover the mouse on specified element and click on the specified element
     *
     * @param hoverele  - The element to be hovered on page
     * @param clickeele - The element to be clicked on page
     * @author Balaji N - Dream Corp
     */

    public void MouseHoverAndClick(WebElement hoverele, WebElement clickeele);

    /**
     * This method will take the screenshot of the page
     *
     * @param tname - The test name will be passed as parameter
     * @author Balaji N - Dream Corp
     */

    public String captureScreen(String tname);

    /**
     * This method will upload the file to the element
     *
     * @param ele      - The element to be uploaded
     * @param filepath - The file path to be uploaded the element
     * @author Balaji N - Dream Corp
     */

    public void uploadFile(WebElement ele, String filepath);

    /**
     * This method will upload the file to the element
     *
     * @param ele      - The element to be uploaded
     * @param filepath - The file path to be downloaded location
     *                 filename - The downloaded file name
     * @author Balaji N - Dream Corp
     */

    public void VerifyFileDownLoad(WebElement ele, String pathfile, String filename);

    /**
     * This method will handle the analog clock time
     *
     * @param TimeValue - The time value to be passed as parameter
     * @param ele       - The element to be verified
     * @author Balaji N - Dream Corp
     */

    public void HandleAnalogClock(String TimeValue, WebElement ele);

    /**
     * This method will get the current time from the system
     *
     * @author Balaji N - Dream Corp
     */

    public String GetCurrentTime();

    /**
     * This method will verify the element is displayed on page or not
     *
     * @author Balaji N - Dream Corp
     */

    public boolean verifyIsDisplayed(WebElement ele);

    /**
     * This method will verify the element is enabled on page or not
     *
     * @author Balaji N - Dream Corp
     */

    public boolean verifyIsEnabled(WebElement ele);

    /**
     * This method will verify the element is selected on page or not
     *
     * @author Balaji N - Dream Corp
     */

    public boolean verifyIsSelected(WebElement ele);

    /**
     * This method will perform right click action
     *
     * @author Balaji N - Dream Corp
     */

    public void rightClickAction();

    /**
     * This method will handle the drag and drop actions using action class
     *
     * @author Balaji N - Dream Corp
     */

    public void dragAndDrop(WebElement source, WebElement target);

    /**
     * This method will return the column count of the table
     *
     * @param ele - The table column element
     * @author Balaji N - Dream Corp
     */

    public int getColumncount(WebElement ele);

    /**
     * This method will return the row count of the table
     *
     * @param ele - The table row element
     * @author Balaji N - Dream Corp
     */

    public int getRowCount(WebElement ele);

    public void HandleAutocomplete(WebElement ele, String data);


    /**
     * This function will return the current system local date
     *
     * @return It return the current date from local system, otherwise it return null
     * @Author: Balaji N
     */
    public String CurrentDate();
}
