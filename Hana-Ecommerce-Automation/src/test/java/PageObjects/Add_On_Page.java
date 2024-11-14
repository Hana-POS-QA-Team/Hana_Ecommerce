package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Add_On_Page extends BasePage {
    public Add_On_Page(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//p[@class='subtitle']")
    private WebElement add_on_page_title;

    @FindBy(xpath = "(//div[@class='col-md-3'])[2]//div[3]//div[1]//input[@id='Medium Box_2']")
    private WebElement add_on_chocolates_medium_checkbox;

    @FindBy(xpath = "(//a[@class='readmore'])[1]")
    private WebElement continueToCheckoutBtn;


    /**
     * Validate whether add on page title is displayed or not
     *
     * @return If add on page title is displayed then true else false
     * @Description: This function highlights the add-on page title, and then validates whether add on page title is displayed or not
     * Author: Balaji N
     */
    public boolean verify_add_on_page_title() {
        HighlightElement(add_on_page_title);
        return add_on_page_title.isDisplayed();
    }

    /**
     * Click on add-on chocolates as medium checkbox
     *
     * @Description: This function highlights the add-on chocolates as medium checkbox, and then clicks on it
     * Author: Balaji N
     */
    public void Click_add_on_chocolates_medium_checkbox() {
        // fluentWait(add_on_chocolates_medium_checkbox);
        add_on_chocolates_medium_checkbox.click();
        //  jsClick(add_on_chocolates_medium_checkbox);
    }

    /**
     * Click on continue to checkout button
     *
     * @Description: This function highlights the continue to checkout button, and then clicks on it
     * Author: Balaji N
     */
    public void Click_continueToCheckoutBtn() {
        jsScrollClick(continueToCheckoutBtn);
    }

}
