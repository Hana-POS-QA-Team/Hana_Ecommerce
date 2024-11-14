package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }


    //============ Global Search elements ============
    @FindBy(xpath = "//input[@id='txtSearch']")
    private WebElement global_search_textBox;

    @FindBy(xpath = "//button[@id='BtnSearch']")
    private WebElement global_search_btn;


//======================= Home Page Functions =======================

    /**
     * This method will get the home page url
     *
     * @return
     * @Author Balaji N
     */
    public String get_HomePage() {
        return getDriver().getCurrentUrl();
    }

    /**
     * Search the product in Global Search and click on search button on home page
     *
     * @param productName product name to be searched
     * @Author Balaji N
     */
    public void Search_Product_In_GlobalSearch(String productName) {
        fluentWait(global_search_textBox);
        global_search_textBox.click();
        jsClearAndType(global_search_textBox, productName);
        delayWithGivenTime(1000);
        jsClick(global_search_btn);
    }

}
