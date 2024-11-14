package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SearchResults_Page extends BasePage {

    public SearchResults_Page(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[@id='CatName']")
    private WebElement search_result_title;

    @FindBy(xpath = "//a[@class='product-name']")
    private WebElement product_displayed_description;


    //======================= Search Result Functions =======================

    /**
     * Validates whether the search result page is displayed or not
     *
     * @return If search result page is displayed then true else false
     * Author Balaji N
     */
    public boolean Verify_Search_Result_Page_IsDisplayed() {
        HighlightElement(search_result_title);
        return search_result_title.isDisplayed();
    }

    /**
     * Clicks the product displayed on search result page
     * <p>
     * This function will click the product displayed on search result page
     * Author Balaji N
     */
    public void Click_Product_Displayed_On_Search_Result_Page() {
        jsClick(product_displayed_description);
    }

}
