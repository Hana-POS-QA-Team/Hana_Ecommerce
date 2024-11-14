package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Order_Preview_Page extends BasePage {
    public Order_Preview_Page(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h4[@class='alert alert-info']")
    private WebElement OrderPreviewPage;

    @FindBy(xpath = "//div[@class='col-lg-4 col-md-4 col-sm-12 col-xs-12 col-12 summarytext']//h4/following::h2")
    private WebElement product_item_and_variant;

    @FindBy(xpath = "//div[@class='col-lg-4 col-md-4 col-sm-12 col-xs-12 col-12 summarytext']//h4/following::h3[1]")
    private WebElement product_qty_and_price;

    @FindBy(xpath = "//div[@class='col-lg-4 col-md-4 col-sm-12 col-xs-12 col-12 summarytext']//div//p")
    private WebElement addon_product_item_and_variant_price;

    @FindBy(xpath = "//div[@class='col-lg-4 col-md-4 col-sm-12 col-xs-12 col-12 summarytext']//div//small")
    private WebElement addon_product_qty_and_price;

    @FindBy(xpath = "//strong[@class='previewCartCheckout-price']")
    private WebElement sub_total;

    @FindBy(xpath = "//a[@id='CheckOut']")
    private WebElement proceed_to_checkout_button;


    //========================= Order Preview Page functions ==============================

    /**
     * Validate whether the order preview page is displayed or not
     *
     * @return If order preview page is displayed then true else false
     * @Description : This function will validate whether the order preview page is displayed or not
     * Author Balaji N
     */
    public boolean Verify_OrderPreviewPage() {
        HighlightElement(OrderPreviewPage);
        return OrderPreviewPage.isDisplayed();
    }

    /**
     * It retrieves the product item and variant from order preview page
     *
     * @return If product item and variant is displayed then the product item and variant from order preview page
     * @Description : This function will retrieve the product item and variant from order preview page
     * Author Balaji N
     */
    public String get_product_item_and_variant() {
        HighlightElement(product_item_and_variant);
        return (product_item_and_variant.getText());
    }

    /**
     * It retrieves the product quantity and price from order preview page
     *
     * @return If product quantity and price is displayed then the product quantity and price from order preview page
     * @Description : This function will retrieve the product quantity and price from order preview page
     * Author Balaji N
     */
    public String get_product_qty_and_price() {
        HighlightElement(product_qty_and_price);
        return (product_qty_and_price.getText());
    }

    /**
     * It retrieves the addon product item and variant price from order preview page
     *
     * @return If addon product item and variant price is displayed then the addon product item and variant price from order preview page
     * @Description : This function will retrieve the addon product item and variant price from order preview page
     * Author Balaji N
     */
    public String get_addon_product_item_and_variant_price() {
        HighlightElement(addon_product_item_and_variant_price);
        return (addon_product_item_and_variant_price.getText());
    }

    /**
     * It retrieves the addon product quantity and price from order preview page
     *
     * @return If addon product quantity and price is displayed then the addon product quantity and price from order preview page
     * @Description : This function will retrieve the addon product quantity and price from order preview page
     * Author Balaji N
     */
    public String get_addon_product_qty_and_price() {
        HighlightElement(addon_product_qty_and_price);
        return addon_product_qty_and_price.getText();
    }

    /**
     * It retrieves the subtotal from order preview page
     *
     * @return If subtotal is displayed then the subtotal from order preview page
     * @Description : This function will retrieve the subtotal from order preview page
     * Author Balaji N
     */
    public String get_subtotal_on_order_preview_page() {
        HighlightElement(sub_total);
        return sub_total.getText();
    }

    /**
     * It calculates the subtotal from order preview page
     *
     * @return If subtotal is displayed then the subtotal from order preview page
     * @Description : This function will calculate the subtotal from order preview page
     * Author Balaji N
     */
    public Double calculated_subtotal() {
        Double product = Double.parseDouble(product_qty_and_price.getText().replace("1 × $", "").trim());
        Double addon = Double.parseDouble(addon_product_qty_and_price.getText().replace("1 × $", "").trim());
        return product + addon;
    }

    /**
     * It clicks on proceed to checkout button
     *
     * @Description : This function will click on proceed to checkout button
     * Author Balaji N
     */
    public void Click_proceed_to_checkout_button() {
        click(proceed_to_checkout_button);
    }

}
