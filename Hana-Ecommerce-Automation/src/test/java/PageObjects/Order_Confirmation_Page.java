package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Order_Confirmation_Page extends BasePage {
    public Order_Confirmation_Page(WebDriver driver) {
       super(driver);
    }

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[1]//p)[1]")
    private WebElement product_row1;

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[1]//p)[3]")
    private WebElement product_row2;

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[2])[1]")
    private WebElement quantity_row1;

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[2])[2]")
    private WebElement quantity_row2;

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[3])[1]")
    private WebElement unit_price_row1;

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[3])[2]")
    private WebElement unit_price_row2;

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[4])[1]")
    private WebElement total_price_row1;

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[4])[2]")
    private WebElement total_price_row2;

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[2])[3]")
    private WebElement product_cost;

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[2])[4]")
    private WebElement delivery_fee;

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[2])[5]")
    private WebElement tax;

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[2])[6]")
    private WebElement total_product_cost;

    @FindBy(xpath = "(//table[@class='table table-striped table-hover']//tbody//tr//td)[18]")
    private WebElement customer_information;

    @FindBy(xpath = "(//table[@class='table table-striped table-hover']//tbody//tr//td)[20]")
    private WebElement delivery_information;

    @FindBy(xpath = "(//table[@class='info_table table table-striped']//td[2])[1]")
    private WebElement payment_type;

    @FindBy(xpath = "(//table[@class='info_table table table-striped']//td[2])[2]")
    private WebElement payment_card_number;

    @FindBy(xpath = "(//div[@class='col-md-6'])[6]//p")
    private WebElement commentsOrspecialInstructions;


    public double Calculate_Total_Cost() {
        double Product_cost = Double.parseDouble(product_cost.getText().replace("$", "").trim());
        double Delivery_fee = Double.parseDouble(delivery_fee.getText().replace("$", "").trim());
        double Tax = Double.parseDouble(tax.getText().replace("$", "").trim());
        // double Total_product_cost = Double.parseDouble(total_product_cost.getText().replace("$", "").trim());
        double Total_cost = Product_cost + Delivery_fee + Tax;
        System.out.println("Product cost is " + Product_cost);
        System.out.println("Delivery fee is " + Delivery_fee);
        System.out.println("Tax is " + Tax);
        System.out.println("Total cost of order is " + Total_cost);
        return Total_cost;
    }


    // Getter method for Product Row 1
    public String getProductRow1Text() {
        HighlightElement(product_row1);
        return product_row1.getText();
    }

    // Getter method for Product Row 2
    public String getProductRow2Text() {
        HighlightElement(product_row2);
        return product_row2.getText();
    }

    // Getter method for Quantity Row 1
    public String getQuantityRow1Text() {
        HighlightElement(quantity_row1);
        return quantity_row1.getText();
    }

    // Getter method for Quantity Row 2
    public String getQuantityRow2Text() {
        HighlightElement(quantity_row2);
        return quantity_row2.getText();
    }

    // Getter method for Unit Price Row 1
    public String getUnitPriceRow1Text() {
        HighlightElement(unit_price_row1);
        return unit_price_row1.getText();
    }

    // Getter method for Unit Price Row 2
    public String getUnitPriceRow2Text() {
        HighlightElement(unit_price_row2);
        return unit_price_row2.getText();
    }

    // Getter method for Total Price Row 1
    public String getTotalPriceRow1Text() {
        HighlightElement(total_price_row1);
        return total_price_row1.getText();
    }

    // Getter method for Total Price Row 2
    public String getTotalPriceRow2Text() {
        HighlightElement(total_price_row2);
        return total_price_row2.getText();
    }

    // Getter method for Product Cost
    public String getProductCostText() {
        HighlightElement(product_cost);
        return product_cost.getText();
    }

    // Getter method for Delivery Fee
    public String getDeliveryFeeText() {
        HighlightElement(delivery_fee);
        return delivery_fee.getText();
    }

    // Getter method for Tax
    public String getTaxText() {
        HighlightElement(tax);
        return tax.getText();
    }

    // Getter method for Total Product Cost
    public String getTotalProductCostText() {
        HighlightElement(total_product_cost);
        return total_product_cost.getText();
    }

    // Getter method for Customer Information
    public String getCustomerInformationText() {
        HighlightElement(customer_information);
        return customer_information.getText();
    }

    // Getter method for Delivery Information
    public String getDeliveryInformationText() {
        HighlightElement(delivery_information);
        return delivery_information.getText();
    }

    // Getter method for Payment Type
    public String getPaymentTypeText() {
        HighlightElement(payment_type);
        return payment_type.getText();
    }

    // Getter method for Payment Card Number
    public String getPaymentCardNumberText() {
        HighlightElement(payment_card_number);
        return payment_card_number.getText();
    }

    // Getter method for Comments/Special Instructions
    public String getCommentsOrSpecialInstructionsText() {
        HighlightElement(commentsOrspecialInstructions);
        return commentsOrspecialInstructions.getText();
    }


}
