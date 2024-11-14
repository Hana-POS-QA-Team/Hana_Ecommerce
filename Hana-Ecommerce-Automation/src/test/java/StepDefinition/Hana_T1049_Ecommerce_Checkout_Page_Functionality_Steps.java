package StepDefinition;


import PageObjects.*;
import Utilities.CustomSoftAssert;
import Utilities.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumProjectBase.BaseClass;

import static seleniumProjectBase.BaseClass.prop;

public class Hana_T1049_Ecommerce_Checkout_Page_Functionality_Steps {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private HomePage ecommerce_homePage;
    private SearchResults_Page searchResultsPage;
    private ProductDetails_Page productDetailsPage;
    private Add_On_Page addOnPage;
    private Order_Preview_Page orderPreviewPage;
    private Checkout_Page checkoutPage;
    private Order_Confirmation_Page order_confirmationPage;

    CustomSoftAssert softassert = new CustomSoftAssert();
    BaseClass base = new BaseClass();

    @Given("the user launches the Ecommerce website {string}")
    public void the_user_launches_the_ecommerce_website(String ecommerce_checkoutpage_url) {
        lp = new LoginPage(BaseClass.getDriver());
        softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");

        lp.EnterUserName(prop.getProperty("bestuname"));
        lp.EnterPassword(prop.getProperty("bestpass"));
        softassert.assertEquals(lp.get_entered_username(), prop.getProperty("bestuname"), "Test Step - 1: Entered username is not matching with expected username");
        softassert.assertEquals(lp.get_entered_password(), prop.getProperty("bestpass"), "Test Step - 1: Entered password is not matching with expected password");
        lp.ClickLoginButton();

        dashboard = new HanaDashBoardPage(BaseClass.getDriver());

        softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 1: Hana dashboard page is not displayed");
        dashboard.SelectShopNameDropDown(prop.getProperty("bestshopname"));
        softassert.assertEquals(dashboard.get_selected_shopname_from_hanadashboard(), prop.getProperty("bestshopname"), "Test Step - 1: Selected shopname is not matching with expected shopname");
        base.delayWithGivenTime(2000);
        dashboard.Click_ProfileIcon_On_HanaDashBoardPage_And_Clicks_User_Website();

        ecommerce_homePage = new HomePage(BaseClass.getDriver());
        base.delayWithGivenTime(3000);
        softassert.assertEquals(ecommerce_homePage.get_HomePage(), ecommerce_checkoutpage_url, "Test Step - 1: Ecommerce home page url is not matching with expected url");

    }

    @Then("the respective Ecommerce website should be displayed")
    public void the_respective_ecommerce_website_should_be_displayed() {

    }

    @When("the user searches for the product {string} under the search textbox and clicks on the search button")
    public void the_user_searches_for_the_product_under_the_search_textbox_and_clicks_on_the_search_button(String ecommerce_product) {
        ecommerce_homePage.Search_Product_In_GlobalSearch(ecommerce_product);
    }

    @Then("the search results page should be displayed")
    public void the_search_results_page_should_be_displayed() {
        searchResultsPage = new SearchResults_Page(BaseClass.getDriver());
        softassert.assertTrue(searchResultsPage.Verify_Search_Result_Page_IsDisplayed(), "Test Step - 2: Search result page is not displayed");
    }

    @When("the user clicks on the product {string} from the search results")
    public void the_user_clicks_on_the_product_from_the_search_results(String string) {
        searchResultsPage.Click_Product_Displayed_On_Search_Result_Page();
        base.delayWithGivenTime(2000);
    }

    @Then("the product page should be displayed")
    public void the_product_page_should_be_displayed() {
        productDetailsPage = new ProductDetails_Page(BaseClass.getDriver());
        base.delayWithGivenTime(1000);
        softassert.assertEquals(productDetailsPage.Verify_Product_Displayed_Description(), prop.getProperty("ecommerce_product"), "Test Step - 4: Product description is not matching with expected product description");
    }

    @When("the user enters\\/selects all mandatory fields and clicks on the order button")
    public void the_user_enters_selects_all_mandatory_fields_and_clicks_on_the_order_button() {
        base.delayWithGivenTime(2000);
        productDetailsPage.Click_Product_Variant_As_Medium();
        base.delayWithGivenTime(1000);
        softassert.assertEquals(productDetailsPage.Verify_Displayed_Product_Price(), prop.getProperty("product_variant_medium_price"), "Test Step - 4: Product variant is not matching with expected product variant");
        base.delayWithGivenTime(2000);
        productDetailsPage.Click_Delivery_Tab();
        base.delayWithGivenTime(2000);
        productDetailsPage.Click_Delivery_Date_As_Current_Date();
        base.delayWithGivenTime(3000);
        //  softassert.assertEquals(productDetailsPage.Validate_Delivery_Date_As_Current_Date_Displayed_In_Green_Colour(), "rgba(0, 128, 0, 1)", "Test Step - 4: Selected Delivery date is not highlighted in green colour");
        productDetailsPage.Enter_Zip_Code(prop.getProperty("ecommerce_zipcode"));
        base.delayWithGivenTime(2000);
        productDetailsPage.Click_Check_Button();
        base.delayWithGivenTime(2000);
        softassert.assertEquals(productDetailsPage.Verify_Zip_Code_Validation_Message(), "Delivery Available 77021", "Test Step - 4: Validation message is not matching with expected validation message");
        base.delayWithGivenTime(2000);
        productDetailsPage.Click_Create_Order_Button();

    }

    @Then("the addons page should be displayed")
    public void the_addons_page_should_be_displayed() {
        base.delayWithGivenTime(3000);
        addOnPage = new Add_On_Page(BaseClass.getDriver());
        softassert.assertTrue(addOnPage.verify_add_on_page_title(), "Test Step - 5: Add - On page is not displayed");
        base.delayWithGivenTime(2000);
    }

    @When("the user selects an addon and clicks on the {string} button")
    public void the_user_selects_an_addon_and_clicks_on_the_button(String string) {
        addOnPage.Click_add_on_chocolates_medium_checkbox();
        base.delayWithGivenTime(2000);
        addOnPage.Click_continueToCheckoutBtn();
    }

    @Then("the order preview page should be displayed")
    public void the_order_preview_page_should_be_displayed() {
        base.delayWithGivenTime(3000);
        orderPreviewPage = new Order_Preview_Page(BaseClass.getDriver());
        base.delayWithGivenTime(1000);
        softassert.assertTrue(orderPreviewPage.Verify_OrderPreviewPage(), "Test Step - 5: Order preview page is not displayed");
    }

    @Then("the sub total of the product {string} with variant price {string} should be displayed properly")
    public void the_sub_total_of_the_product_with_variant_price_should_be_displayed_properly(String string, String string2) {
        base.delayWithGivenTime(2000);
        softassert.assertEquals(orderPreviewPage.get_product_item_and_variant(), "Balaji_Test_Product-Medium", "Test Step - 6: Product item and variant is not matching with expected product item and variant");
        softassert.assertEquals(orderPreviewPage.get_product_qty_and_price(), "1 × $500.00", "Test Step - 6: Product item and variant is not matching with expected product item and variant");
        softassert.assertEquals(orderPreviewPage.get_addon_product_item_and_variant_price(), "Chocolates - Medium Box add-on for Test_Automation", "Test Step - 6: Product item and variant is not matching with expected product item and variant");
        softassert.assertEquals(orderPreviewPage.get_addon_product_qty_and_price(), "1 × $19.99", "Test Step - 6: Product item and variant is not matching with expected product item and variant");
        softassert.assertEquals(orderPreviewPage.get_subtotal_on_order_preview_page(), "$" + orderPreviewPage.calculated_subtotal(), "Test Step - 6: Sub-Total price is not matching with expected total price");
    }

    @When("the user clicks on the {string} button")
    public void the_user_clicks_on_the_button(String string) {
        orderPreviewPage.Click_proceed_to_checkout_button();
        base.delayWithGivenTime(3000);
        checkoutPage = new Checkout_Page(BaseClass.getDriver());

    }

    @Then("the checkout page should be displayed")
    public void the_checkout_page_should_be_displayed() {
        softassert.assertTrue(checkoutPage.Verify_CheckoutPage(), "Test Step - 7: Checkout page is not displayed");
        base.delayWithGivenTime(3000);

    }

    @When("the user selects the occasion {string} under the Cart tab")
    public void the_user_selects_the_occasion_under_the_cart_tab(String string) {
        checkoutPage.Select_Occasion(prop.getProperty("cart_occasion"));
        base.delayWithGivenTime(1000);

    }

    @Then("the respective occasion value should be displayed in the respective field")
    public void the_respective_occasion_value_should_be_displayed_in_the_respective_field() {
        softassert.assertEquals(checkoutPage.get_selected_occasion_on_cartTab_checkoutPage(), prop.getProperty("cart_occasion"), "Test Step - 7: Selected occasion is not matching with expected occasion");
        base.delayWithGivenTime(1000);
    }

    @When("the user enters the email {string} in the Email field")
    public void the_user_enters_the_email_in_the_email_field(String string) {
        if (checkoutPage.Verify_Message_Popup_isDisplay() == true) {
            checkoutPage.Click_on_outside_from_messagePopup();
        }

        checkoutPage.Enter_Email_on_cartTab_checkoutPage(prop.getProperty("cart_email"));
        base.delayWithGivenTime(1000);
    }

    @Then("the respective value should be displayed in the respective field")
    public void the_respective_value_should_be_displayed_in_the_respective_field() {
        softassert.assertEquals(checkoutPage.get_entered_email_on_cartTab_checkoutPage(), prop.getProperty("cart_email"), "Test Step - 7: Entered email is not matching with expected email");
        base.delayWithGivenTime(1000);
    }

    @When("the user enters the card message {string} in the Card Message field")
    public void the_user_enters_the_card_message_in_the_card_message_field(String string) {
        checkoutPage.Click_card_message_from_Suggestion(prop.getProperty("cart_card_message"));
        base.delayWithGivenTime(1000);
        softassert.assertEquals(checkoutPage.get_entered_card_message_on_cartTab_checkoutPage(), prop.getProperty("cart_card_message"), "Test Step - 7: Entered card message is not matching with expected card message");
    }

    @Then("the Delivery \\/ Pickup section should be displayed")
    public void the_delivery_pickup_section_should_be_displayed() {
        base.delayWithGivenTime(1000);
        checkoutPage.Click_cart_tab_Continue_toDelivery_PickUp_button();
        base.delayWithGivenTime(2000);
        softassert.assertTrue(checkoutPage.verify_recipient_information_subtitle(), "Test Step - 7: Recipient information subtitle is not displayed");
    }

    @When("the user enters the recipient phone number {string} in the Recipient Phone Number field")
    public void the_user_enters_the_recipient_phone_number_in_the_recipient_phone_number_field(String string) {

    }

    @When("the user enters the address {string} and selects an address from the suggestion dropdown")
    public void the_user_enters_the_address_and_selects_an_address_from_the_suggestion_dropdown(String string) {

    }

    @Then("the respective address {string} should be displayed in the respective field")
    public void the_respective_address_should_be_displayed_in_the_respective_field(String string) {

    }

    @When("the user selects a delivery date in the Delivery Date field")
    public void the_user_selects_a_delivery_date_in_the_delivery_date_field() {

    }

    @Then("the respective date should be displayed in the respective field")
    public void the_respective_date_should_be_displayed_in_the_respective_field() {

    }

    @Then("the payment section should be displayed")
    public void the_payment_section_should_be_displayed() {

    }

    @When("the user selects {string} as the payment method")
    public void the_user_selects_as_the_payment_method(String string) {

    }

    @Then("the respective value {string} should be displayed in the Payment Method field")
    public void the_respective_value_should_be_displayed_in_the_payment_method_field(String string) {

    }

    @When("the user enters the name {string} in the Name On Card field")
    public void the_user_enters_the_name_in_the_name_on_card_field(String string) {

    }

    @Then("the respective value {string} should be displayed in the respective field")
    public void the_respective_value_should_be_displayed_in_the_respective_field(String string) {

    }

    @When("the user enters the card number {string} in the Card Number field")
    public void the_user_enters_the_card_number_in_the_card_number_field(String string) {

    }

    @When("the user enters the expiry date {string} in the Expiry Date field")
    public void the_user_enters_the_expiry_date_in_the_expiry_date_field(String string) {

    }

    @When("the user enters the CVV {string} in the CVV\\/CVC field")
    public void the_user_enters_the_cvv_in_the_cvv_cvc_field(String string) {

    }

    @When("the user enters {string} in the Billing First Name field")
    public void the_user_enters_in_the_billing_first_name_field(String string) {

    }

    @When("the user enters {string} in the Billing Last Name field")
    public void the_user_enters_in_the_billing_last_name_field(String string) {

    }

    @When("the user enters {string} in the Billing Phone Number field")
    public void the_user_enters_in_the_billing_phone_number_field(String string) {

    }

    @When("the user enters {string} in the Billing Street Address field")
    public void the_user_enters_in_the_billing_street_address_field(String string) {

    }

    @When("the user enters {string} in the Billing Country field")
    public void the_user_enters_in_the_billing_country_field(String string) {

    }

    @When("the user enters {string} in the Billing State field")
    public void the_user_enters_in_the_billing_state_field(String string) {

    }

    @When("the user enters {string} in the Billing City field")
    public void the_user_enters_in_the_billing_city_field(String string) {

    }

    @When("the user enters {string} in the Billing Zip Code field")
    public void the_user_enters_in_the_billing_zip_code_field(String string) {

    }

    @Then("the review section should be displayed")
    public void the_review_section_should_be_displayed() {

    }

    @When("the user verifies if all the entered information is displaying properly")
    public void the_user_verifies_if_all_the_entered_information_is_displaying_properly() {

    }

    @Then("the respective data should be displayed properly")
    public void the_respective_data_should_be_displayed_properly() {

    }

    @When("the user checks the {string} checkbox")
    public void the_user_checks_the_checkbox(String string) {

    }

    @Then("the respective checkbox should be checked")
    public void the_respective_checkbox_should_be_checked() {

    }

    @Then("a success toaster message should be displayed")
    public void a_success_toaster_message_should_be_displayed() {

    }

    @Then("the user should be redirected to the Order Confirmation Page")
    public void the_user_should_be_redirected_to_the_order_confirmation_page() {

    }

    @Then("the order number should be displayed")
    public void the_order_number_should_be_displayed() {

    }

    @Then("all the details entered in the checkout page should be displayed")
    public void all_the_details_entered_in_the_checkout_page_should_be_displayed() {

    }

    @Then("the respective product with all the details should be displayed")
    public void the_respective_product_with_all_the_details_should_be_displayed() {

    }

    @Then("the order total should be displayed properly")
    public void the_order_total_should_be_displayed_properly() {
        softassert.assertAll();
    }

}
