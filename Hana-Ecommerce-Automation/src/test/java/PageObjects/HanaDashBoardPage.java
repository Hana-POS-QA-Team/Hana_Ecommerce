package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HanaDashBoardPage extends BasePage {

    public HanaDashBoardPage(WebDriver driver) {
      super(driver);
    }

    //========================================= Hana Dashboard page web elements =========================
    @FindBy(xpath = "//a[@class='li_Hana navbar-brand']")
    private WebElement HanaLogo;

    @FindBy(xpath = "//a[@id='clsUnRead']/following::button[3]")
    private WebElement NewOrder_Menu_Btn;

    @FindBy(xpath = "//a[@id='clsUnRead']/following::button[5]")
    private WebElement Dispatch_Menu_Btn;

    @FindBy(xpath = "(//button[@class='btn btn-default dropdown-toggle'][normalize-space()='New Order'])")
    private WebElement NewOrderMenuBtn;

    @FindBy(xpath = "(//button[@class='btn btn-default dropdown-toggle'][normalize-space()='Dispatch'])")
    private WebElement DispatchMenuBtn;

    @FindBy(xpath = "//a[@class='li_Dispatch']")
    private WebElement quickDispatch;

    @FindBy(xpath = "//a[@class='li_AdvanceDispatch']")
    private WebElement advanceDispatch;

    @FindBy(xpath = "((//button[@class='btn btn-default dropdown-toggle'][normalize-space()='New Order'])/following::ul//li[1]//a)[1]")
    ////a[@class='li_NewOrder']
    private WebElement OrderEntry;

    @FindBy(xpath = "((//button[@class='btn btn-default dropdown-toggle'][normalize-space()='New Order'])/following::ul//li[2]//a)[1]")
    ////a[@class='li_CashAndCarry']
    private WebElement CashAndCarry;

    @FindBy(xpath = "//span[normalize-space()='Orders']")
    private WebElement OrdersMenu;

    @FindBy(xpath = "//a[normalize-space()='All Orders']")
    private WebElement AllOrdersSubMenu;

    @FindBy(xpath = "//select[@id='ddlShop']")
    private WebElement ShopNameDropDown;

    @FindBy(xpath = "//div[@id='dashboard_datepicker']//div[@class='dashborad-tabs']")
    private WebElement DateSelection;

    @FindBy(xpath = "//span[contains(text(),' Wait Queue ')]")
    private WebElement WaitQueueTab;

    @FindBy(xpath = "//span[@id='lblWaitQueue']")
    private WebElement WaitQueueCount;

    @FindBy(xpath = "//span[contains(text(),' Pending Pickups ')]")
    private WebElement PendingPickupsTab;

    @FindBy(xpath = "//span[@id='lblPickupOrders']")
    private WebElement PendingPickupsCount;

    @FindBy(xpath = "//span[contains(text(),' Pending Deliveries ')]")
    private WebElement PendingDeliveriesTab;

    @FindBy(xpath = "//span[@id='lblAwaitingDispatch']")
    private WebElement PendingDeliveriesCount;

    @FindBy(xpath = "//span[contains(text(),' Pending Confirmations ')]")
    private WebElement PendingConfirmationsTab;

    @FindBy(xpath = "//span[@id='lblUnconfirm']")
    private WebElement PendingConfirmationsCount;

    @FindBy(xpath = "//label[@id='lblWaitcnt']")
    private WebElement WaitQueueIconCount;

    @FindBy(xpath = "//a[@id='idWaitQueue']")
    private WebElement WaitQueueIcon;

    @FindBy(xpath = "(//i[@class='fa fa-2x fa-shopping-basket'])[1]")
    private WebElement PendingPickupIcon;

    @FindBy(xpath = "//span[@id='spnPickupCnt']")
    private WebElement PendingPickupIconCount;

    @FindBy(xpath = "(//i[@class='fa fa-2x fa-clipboard'])[1]")
    private WebElement ViewDraftIcon;

    @FindBy(xpath = "//label[@id='lblDraftcnt']")
    private WebElement ViewDraftIconCount;

    @FindBy(xpath = "(//i[@class='fa fa-2x fa-comment'])[1]")
    private WebElement NewMessageIcon;

    @FindBy(xpath = "//label[@id='lblUnRead']")
    private WebElement NewMessageIconCount;

    @FindBy(xpath = "(//button[normalize-space()='Dispatch'])[1]")
    private WebElement DispatchMenu;

    @FindBy(xpath = "//a[normalize-space()='Advanced Dispatch']")
    private WebElement AdvancedDispatch;

    @FindBy(xpath = "//a[normalize-space()='Quick Dispatch']")
    private WebElement QuickDispatch;

    @FindBy(xpath = "(//i[@data-toggle='tooltip'])[1]")
    private WebElement NewTabPlusIcon;

    @FindBy(xpath = "(//input[@id='top-search1'])[1]")
    private WebElement DashboardSearchBox;

    @FindBy(xpath = "//a[@id='idEmail']")
    private WebElement EmailIcon;

    @FindBy(xpath = "//label[@id='lblEmail']")
    private WebElement EmailIconCount;

    @FindBy(xpath = "//a[@id='CalenderList']")
    private WebElement IntegrateCalenderIcon;

    @FindBy(xpath = "//a[@id='btnRemoteConnect']")
    private WebElement RemoteConnectIcon;

    @FindBy(xpath = "//a[@class='open-small-chat']")
    private WebElement HelpIcon;

    @FindBy(xpath = "//a[@id='hana-profile-menu-link']")
    private WebElement ProfileIcon;

    @FindBy(xpath = "//a[@class='li_LogoutNew common-dynamic-font-size']")
    private WebElement LogoutIcon;

    @FindBy(xpath = "//ul[@class='dropdown-menu']//li//a")
    private List<WebElement> NewOrderList;

    @FindBy(id = "CustomersMenu")
    private WebElement CustomersMenu;

    @FindBy(xpath = "//li[@id='ConfigurationMenu']")
    private WebElement ConfigurationMenu;

    @FindBy(xpath = "//a[@class='li_Configuration']")
    private WebElement settingsSubmenu;

    @FindBy(xpath = "(//span[normalize-space()='Marketing'])[1]")
    private WebElement MarketingMenu;

    @FindBy(xpath = "//a[@class='dropdown-item li_Reminder']")
    private WebElement Reminder_FromMarkingMenu;

    //	Orders Menu Element from the Left menu
    @FindBy(xpath = "//li[@id='OrderMenu']")
    private WebElement OrderMenu;

    //	Confirmation sub menu Element from the Orders Menu in the Left Menu
    @FindBy(xpath = "//a[@id='btnOpenDeliveryConfModalNew']")
    private WebElement ConfirmationSubMenu;

    @FindBy(xpath = "//a[@id='userWebsiteDisplayName']")
    private WebElement userWebsiteDisplayName;

    //======================================= Hana Dashboard page Web Elements =========================
    public String VerifyPageTitleonDashboard() {
        return getDriver().getTitle();
    }


    /**
     * Selects the shop name from the dropdown field on dashboard page
     *
     * @param shopname Provided Shop name to be selected
     * @Description: This function select the visible text of provided shop name from the dropdown field on the hana dashboard page
     * @Author: Balaji N
     */
    public HanaDashBoardPage SelectShopNameDropDown(String shopname) {
        Select select = new Select(ShopNameDropDown);
        select.selectByVisibleText(shopname);
        return this;
    }

    /**
     * It gets the selected shop name from the dropdown field on dashboard page
     *
     * @return If the shop name is selected it returns the selected shop name; otherwise it returns null
     * @Description: This function select the visible text of provided shop name from the dropdown field on the hana dashboard page
     * @Author: Balaji N
     */
    public String get_selected_shopname_from_hanadashboard() {
        Select select = new Select(ShopNameDropDown);
        return select.getFirstSelectedOption().getText();
    }

    /**
     * Validates whether the Hana home icon is present on the hana dashboard page.
     *
     * @return if the password field is displayed on the page it return true; otherwise it returns false
     * @Description: This function highlights the home icon and checks if it is displayed on hana dashboard page.
     * @Author: Balaji N
     */
    public boolean VerifyHanaDashBoardPage() {
        boolean isLogoDisplayed = false;
        HighlightElement(HanaLogo);
        fluentWait(HanaLogo);
        delayWithGivenTime(2000);

        if (HanaLogo.isDisplayed() == false) {
            getDriver().navigate().refresh();
        }

        isLogoDisplayed = HanaLogo.isDisplayed();
        return isLogoDisplayed;
    }

    /**
     * Clicks on the Hana home icon using javascript click.
     *
     * @Description: This function clicks on the Hana home icon using javascript click.
     *
     * <p>
     * Expected functionality: If the Hana home icon is clicked on hana dashboard page, It should be refreshed or reloaded
     * </p>
     * @Author: Balaji N
     */
    public void ClickOnHomeIcon() {
        jsClick(HanaLogo);
    }

    public void NewOrderMenuClick() {
        HighlightElement(NewOrderMenuBtn);
        MouseHover(NewOrderMenuBtn);
        HighlightElement(OrderEntry);
        click(OrderEntry);
    }

    /**
     * Clicks on the Cash and Carry menu option.
     *
     * @Description: This function clicks on the Cash and Carry menu option.
     * @Author: Balaji N
     */
    public void CashAndCarryMenuClick() {
        ThreadWait(2000);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(40));
        WebElement newOrderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='btn btn-default dropdown-toggle'][normalize-space()='New Order'])")));
        fluentWait(NewOrderMenuBtn);
        Actions action = new Actions(getDriver());
        action.moveToElement(NewOrderMenuBtn).build().perform();
        delayWithGivenTime(2000);
        fluentWait(CashAndCarry);
        HighlightElement(CashAndCarry);
        action.moveToElement(CashAndCarry).click().build().perform();

    }

    /**
     * Hovering the mouse and Clicks the Order Entry option on hana dashboard page.
     *
     * @return If the Order Entry option is clicked it returns Cash And Carry Page otherwise it returns null
     * @Description: This function will Hovering the mouse and Clicks the Order Entry option on hana dashboard page it should display the
     * Cash and carry page.
     * @Author: Balaji N
     */
    public void ClickOrderEntry() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        // Wait for the page's JavaScript to complete loading
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        // Optionally, wait for jQuery AJAX calls to finish, if jQuery is present on the page
        Boolean isJQueryPresent = (Boolean) ((JavascriptExecutor) getDriver()).executeScript("return typeof jQuery != 'undefined'");
        if (isJQueryPresent) {
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return jQuery.active == 0"));
        }
        ThreadWait(2000);
        explicitWait(NewOrderMenuBtn, 30);
        WebElement newOrderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='btn btn-default dropdown-toggle'][normalize-space()='New Order'])")));
        fluentWait(NewOrderMenuBtn);
        Actions action = new Actions(getDriver());
        action.moveToElement(NewOrderMenuBtn).build().perform();
        delayWithGivenTime(1500);
        explicitWait(CashAndCarry, 30);
        fluentWait(CashAndCarry);
        ThreadWait(500);
        HighlightElement(OrderEntry);
        explicitWait(OrderEntry, 30);
        action.moveToElement(OrderEntry).click().build().perform();
        //MouseHoverAndClick(NewOrderMenuBtn, OrderEntry);
    }


    public void Hover_Dispatch_And_Click_QuickDispatch() {
        fluentWait(DispatchMenuBtn);
        Actions action = new Actions(getDriver());
        action.moveToElement(DispatchMenuBtn).build().perform();
        delayWithGivenTime(1500);
        fluentWait(quickDispatch);
        ThreadWait(500);
        HighlightElement(quickDispatch);
        action.moveToElement(quickDispatch).click().build().perform();
    }

    public void Hover_Dispatch_And_Click_AdvanceDispatch() {
        fluentWait(DispatchMenuBtn);
        Actions action = new Actions(getDriver());
        action.moveToElement(DispatchMenuBtn).build().perform();
        delayWithGivenTime(1500);
        fluentWait(advanceDispatch);
        ThreadWait(500);
        HighlightElement(advanceDispatch);
        action.moveToElement(advanceDispatch).click().build().perform();
    }

    /**
     * Clicks the Orders menu from the left menu
     *
     * @Description: This function will wait for the Orders menu element using fluent wait on Orders menu and clicks it using jsclick function.
     * @Author: Balaji N
     */
    public void ClickOrder() {
        fluentWait(OrdersMenu);
        jsClick(OrdersMenu);
    }

    public void ClickAllOrder() {
        explicitWait(OrdersMenu);
        MouseHoverAndClick(OrdersMenu, AllOrdersSubMenu);
    }

    public HanaDashBoardPage SelectShopName(String shopname) {
        dropDown(ShopNameDropDown, shopname, "VisibleText");
        return this;
    }

    /**
     * Validates the Order Entry option is displayed on the Hana dashboard page.
     *
     * @return If the Order Entry option is displayed on the page it return true; otherwise it returns false
     * @Description: This function will hover the mouse on New Order Menu button and checks if Order Entry option on hana dashboard page is displayed.
     * @Author: Balaji N
     */
    public boolean VerifyOrderEntryOptionIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        // Wait for the page's JavaScript to complete loading
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        // Optionally, wait for jQuery AJAX calls to finish, if jQuery is present on the page
        Boolean isJQueryPresent = (Boolean) ((JavascriptExecutor) getDriver()).executeScript("return typeof jQuery != 'undefined'");
        if (isJQueryPresent) {
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return jQuery.active == 0"));
        }
        explicitWait(NewOrderMenuBtn);
        MouseHover(NewOrderMenuBtn);
        HighlightElement(OrderEntry);
        return OrderEntry.isDisplayed();
    }

    /**
     * Validates the Cash and carry option is displayed on the Hana dashboard page.
     *
     * @return If the Cash and Carry option is displayed on the page it return true; otherwise it returns false
     * @Description: This function will hover the mouse on New Order Menu button and checks if Cash and carry option on hana dashboard page is displayed.
     * @Author: Balaji N
     */
    public boolean Verify_Cashandcarry_OptionIsDisplayed() {
        delayWithGivenTime(1000);
        MouseHover(NewOrderMenuBtn);
        HighlightElement(CashAndCarry);
        return CashAndCarry.isDisplayed();
    }

    public boolean Verify_QuickDispatch_OptionIsDisplayed() {
        MouseHover(DispatchMenuBtn);
        //HighlightElement(quickDispatch);
        return quickDispatch.isDisplayed();
    }

    public boolean Verify_AdvanceDispatch_OptionIsDisplayed() {
        MouseHover(DispatchMenuBtn);
        //HighlightElement(advanceDispatch);
        return advanceDispatch.isDisplayed();
    }

    public void ClickCustomersMenu() {
        ThreadWait(2000);
        fluentWait(CustomersMenu);
        CustomersMenu.click();
        //jsClick(CustomersMenu);
    }

    public void Click_ConfigurationMenu() {
        jsClick(ConfigurationMenu);
    }


    public void Click_settingsSubmenu() {
        MouseHover(ConfigurationMenu);
        ThreadWait(2000);
        jsClick(settingsSubmenu);
        switchToWindowbyIndex(1);
    }

    public void Click_Marketing_RemainderMenu() {
        MouseHover(MarketingMenu);
        delayWithGivenTime(1000);
        click(Reminder_FromMarkingMenu);
    }

    //	Navigate to Confirmation Sub Menu
    public void MouseAndClick_Confirmation_sub_menu() {
        MouseHover(OrderMenu);
        delayWithGivenTime(1000);
        click(ConfirmationSubMenu);
    }

    /**
     * Clicks the Profile icon on the Hana dashboard page.
     *
     * @Description: This function will click the Profile icon on the Hana dashboard page.
     * @Author: Balaji N
     */
    public void Click_ProfileIcon_On_HanaDashBoardPage_And_Clicks_User_Website() {
        fluentWait(ProfileIcon);
        HighlightElement(ProfileIcon);
        explicitWait(ProfileIcon);
        Actions action = new Actions(getDriver());
        action.moveToElement(ProfileIcon).build().perform();
        delayWithGivenTime(2000);
        if (userWebsiteDisplayName.isDisplayed() == true) {
            click(userWebsiteDisplayName);
        }
        switchToWindowbyIndex(1);
    }


}
