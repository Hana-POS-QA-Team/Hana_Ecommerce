package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetails_Page extends BasePage {

    public ProductDetails_Page(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[@class='hd-after product_title']")
    private WebElement product_displayed_description;

    @FindBy(xpath = "//div[@class='front-end box' and .//span[contains(text(), 'Medium')]]")
    private WebElement product_variant_as_medium;

    @FindBy(xpath = "//div[@class='pprice']")
    private WebElement product_displayed_price;

    @FindBy(id = "tabDelivery")
    private WebElement delivery_tab;

    @FindBy(xpath = "(//button[@class='btnDate'])[1]")
    private WebElement delivery_date_as_current_date;

    @FindBy(xpath = "//input[@id='txtZipCode']")
    private WebElement zip_code_textbox;

    @FindBy(xpath = "//button[contains(text(),'Check')]")
    private WebElement check_button;

    @FindBy(xpath = "//span[@id='ZipcodeMsg']")
    private WebElement zip_code_validation_message;

    @FindBy(xpath = "//a[@class='readmore btnCreateOrder']")
    private WebElement create_order_button;

    /**
     * It retrieves the text of the product displayed on product details page
     *
     * @return If product details page is displayed then the text of the product displayed on product details page
     * Author Balaji N
     */
    public String Verify_Product_Displayed_Description() {
        HighlightElement(product_displayed_description);
        return product_displayed_description.getText();
    }

    /**
     * Clicks the product variant as medium on product details page
     * <p>
     * Author Balaji N
     */
    public void Click_Product_Variant_As_Medium() {
        jsClick(product_variant_as_medium);
    }

    /**
     * Retrieves the price of the product displayed on product details page
     *
     * @return If product details page is displayed then the price of the product displayed on product details page
     * Author Balaji N
     */
    public String Verify_Displayed_Product_Price() {
        HighlightElement(product_displayed_price);
        return product_displayed_price.getText();
    }

    /**
     * Clicks the delivery tab on product details page
     * Author Balaji N
     */
    public void Click_Delivery_Tab() {
        jsClick(delivery_tab);
    }

    /**
     * Clicks the delivery date as current date on product details page
     * Author Balaji N
     */
    public void Click_Delivery_Date_As_Current_Date() {
        jsClick(delivery_date_as_current_date);
    }

    /**
     * Retrieves the background color of the delivery date as current date on product details page
     * Author Balaji N
     */
    public String Validate_Delivery_Date_As_Current_Date_Displayed_In_Green_Colour() {
        return delivery_date_as_current_date.getCssValue("background-color");
    }

    /**
     * Enters the zip code on product details page
     *
     * @param zip_code The given zip code to be entered
     *                 Author Balaji N
     */
    public void Enter_Zip_Code(String zip_code) {
        fluentWait(zip_code_textbox);
        zip_code_textbox.clear();
        zip_code_textbox.click();
        zip_code_textbox.sendKeys(zip_code);

        // clickAndType(zip_code_textbox, zip_code);
    }

    /**
     * Clicks the check button on product details page
     * Author Balaji N
     */
    public void Click_Check_Button() {
        jsClick(check_button);
    }

    /**
     * Retrieves the zip code validation message on product details page
     *
     * @return If product details page is displayed then the zip code validation message on product details page
     * Author Balaji N
     */
    public String Verify_Zip_Code_Validation_Message() {
        HighlightElement(zip_code_validation_message);
        return zip_code_validation_message.getText();
    }

    /**
     * Clicks the create order button on product details page
     * Author Balaji N
     */
    public void Click_Create_Order_Button() {
        jsClick(create_order_button);
    }

}
