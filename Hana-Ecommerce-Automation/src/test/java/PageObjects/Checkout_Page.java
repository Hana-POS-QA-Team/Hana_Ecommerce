package PageObjects;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Checkout_Page extends BasePage {

    public Checkout_Page(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "(//h4[@class='info-text'])[1]")
    private WebElement checkout_page_heading;

    @FindBy(id = "ddlOccasion")
    private WebElement cart_tab_Occasion_dropdown;

    @FindBy(id = "txtEmail")
    private WebElement cart_tab_email_textbox;

    @FindBy(xpath = "//textarea[@id='txtCardMessage']")
    private WebElement cart_tab_message_textarea;

    @FindBy(xpath = "//div[@class='DivCardMessage']")
    private List<WebElement> list_of_card_message_textarea;

    @FindBy(xpath = "//button[@id='btnCustomNext']")
    private WebElement cart_tab_Continue_toDelivery_PickUp_button;

    //===================== Recipient Information =====================

    @FindBy(id = "spnDelConfim")
    private WebElement recipient_information_subtitle;

    @FindBy(xpath = "//input[@id='txtFirstName']")
    private WebElement recipient_first_name_textbox;

    @FindBy(xpath = "//input[@id='txtLastName']")
    private WebElement recipient_last_name_textbox;

    @FindBy(xpath = "//input[@id='txtPhoneNumber']")
    private WebElement recipient_phone_number_textbox;

    @FindBy(xpath = "//input[@id='txtCompany']")
    private WebElement business_name_textbox;

    @FindBy(xpath = "(//div[@class='pac-container pac-logo hdpi'])[2]//div//span[2]")
    private List<WebElement> list_of_businessName_googleAutoComplete;

    @FindBy(xpath = "(//div[@class='pac-container pac-logo hdpi'])[2]//div//span[3]")
    private List<WebElement> list_of_business_address_street_Country_googleAutoComplete;

    @FindBy(xpath = "//input[@id='txtStreetAddress']")
    private WebElement recipient_street_address_textbox;

    @FindBy(xpath = "//select[@id='ddlDeliveryCountry']")
    private WebElement recipient_country_dropdown;

    @FindBy(xpath = "//select[@id='ddlState']")
    private WebElement recipient_state_dropdown;

    @FindBy(xpath = "//input[@id='txtCity']")
    private WebElement recipient_city_textbox;

    @FindBy(xpath = "//input[@id='txtZipCode']")
    private WebElement recipient_zip_code_textbox;

    @FindBy(xpath = "//input[@id='txtDeliveryDate']")
    private WebElement recipient_delivery_date_textbox;

    @FindBy(xpath = "//button[@id='btnCustomNextbottom']")
    private WebElement continue_to_payment_button;

    @FindBy(xpath = "//p[@id='DisplayMessage']")
    private WebElement display_message_popup;

    @FindBy(xpath = "(//div[@class='modal-content'])[1]")
    private WebElement display_message_popup_content;

    @FindBy(xpath = "(//button[@class='close'])[1]")
    private WebElement display_message_popup_close_button;

    @FindBy(xpath = "//h4[contains(text(),'Payment Information ')]")
    private WebElement payment_information_subtitle;

    //==================== Payments Info elements ==================

    @FindBy(id = "ddlPaymentMethod")
    private WebElement payment_method_dropdown;

    @FindBy(xpath = "//input[@id='txtNameOnCard']")
    private WebElement name_on_card_textbox;

    @FindBy(xpath = "//input[@id='txtCardNumber']")
    private WebElement card_number_textbox;

    @FindBy(xpath = "//input[@id='ddlExpiryDate']")
    private WebElement expiry_date_textbox;

    @FindBy(xpath = "//input[@id='txtCVC']")
    private WebElement ccvc_textbox;

    //============ Billing Info elements ==================

    @FindBy(xpath = "//h4[contains(text(),'Your Billing Information ')]")
    private WebElement billing_information_subtitle;

    @FindBy(xpath = "//input[@id='txtBillingFirstName']")
    private WebElement billing_first_name_textbox;

    @FindBy(xpath = "//input[@id='txtBillingLastName']")
    private WebElement billing_last_name_textbox;

    @FindBy(xpath = "//input[@id='txtBillingPhoneNumber']")
    private WebElement billing_phone_number_textbox;

    @FindBy(xpath = "//input[@id='txtBillingStreetAddress']")
    private WebElement billing_street_address_textbox;

    @FindBy(xpath = "//select[@id='ddlBillingCountry']")
    private WebElement billing_country_dropdown;

    @FindBy(xpath = "//select[@id='ddlBillingState']")
    private WebElement billing_state_dropdown;

    @FindBy(xpath = "//input[@id='txtBillingCity']")
    private WebElement billing_city_textbox;

    @FindBy(xpath = "//input[@id='txtBillingZipCode']")
    private WebElement billing_zip_code_textbox;

    @FindBy(xpath = "//button[@id='btnCustomNext']")
    private WebElement continue_to_review_button;

    @FindBy(xpath = "//h4[contains(text(),'Review')]")
    private WebElement review_information_subtitle;

    @FindBy(xpath = "//input[@id='chkSubstitution']")
    private WebElement substitution_policy_checkbox;

    @FindBy(xpath = "//button[@id='btnCustomNextbottom']")
    private WebElement place_order_button;

    @FindBy(xpath = "//h1[contains(text(),'Order Confirmation')]")
    private WebElement order_confirmation_heading;


    /**
     * Validate whether the checkout page is displayed or not
     *
     * @return Identify whether the checkout page is displayed or not
     * @Author: Balaji N
     */
    public boolean Verify_CheckoutPage() {
        HighlightElement(checkout_page_heading);
        return checkout_page_heading.isDisplayed();
    }

    /**
     * Select the occasion on cart tab in checkout page
     *
     * @param occasion given occasion to be selected
     * @Author: Balaji N
     */
    public void Select_Occasion(String occasion) {
        dropDown(cart_tab_Occasion_dropdown, occasion, "VisibleText");
    }

    /**
     * It retrieves the selected occasion on cart tab in checkout page
     *
     * @return If  occasion selected value is displayed on checkout page it returns the value of occasion selected value, otherwise it returns null string
     * @Author: Balaji N
     */
    public String get_selected_occasion_on_cartTab_checkoutPage() {
        HighlightElement(cart_tab_Occasion_dropdown);
        Select select = new Select(cart_tab_Occasion_dropdown);
        return select.getFirstSelectedOption().getText();
    }

    /**
     * Enters the email on cart tab in checkout page
     *
     * @param email The given email to be entered
     * @Author: Balaji N
     */
    public void Enter_Email_on_cartTab_checkoutPage(String email) {
        clickAndType(cart_tab_email_textbox, email);
    }

    /**
     * It retrieves the entered email on cart tab in checkout page
     *
     * @return If email entered value is displayed on checkout page it returns the value of email entered value, otherwise it returns null string
     * @Author: Balaji N
     */
    public String get_entered_email_on_cartTab_checkoutPage() {
        HighlightElement(cart_tab_email_textbox);
        return cart_tab_email_textbox.getAttribute("value");
    }

    /**
     * Enters the card message on cart tab in checkout page
     *
     * @param message The given message to be entered
     * @Author: Balaji N
     */
    public void Enter_card_message_on_cartTab_checkoutPage(String message) {
        clickAndType(cart_tab_message_textarea, message);
    }

    /**
     * It retrieves the entered card message on cart tab in checkout page
     *
     * @return If card message entered value is displayed on checkout page it returns the value of card message entered value, otherwise it returns null string
     * @Author: Balaji N
     */
    public String get_entered_card_message_on_cartTab_checkoutPage() {
        HighlightElement(cart_tab_message_textarea);
        return cart_tab_message_textarea.getAttribute("value").trim();
    }

    /**
     * Clicks the card message from suggestion on cart tab in checkout page
     *
     * @param suggestion_card_message The given suggestion card message to be clicked
     * @Author: Balaji N
     */
    public void Click_card_message_from_Suggestion(String suggestion_card_message) {
        for (int i = 0; i < list_of_card_message_textarea.size(); i++) {
            if (list_of_card_message_textarea.get(i).getText().equalsIgnoreCase(suggestion_card_message)) {
                jsClick(list_of_card_message_textarea.get(i));
                break;
            }
        }
    }

    /**
     * Clicks the continue to delivery pick up button on cart tab in checkout page
     * <p>
     *
     * @Author: Balaji N
     */
    public void Click_cart_tab_Continue_toDelivery_PickUp_button() {
        jsClick(cart_tab_Continue_toDelivery_PickUp_button);
    }

    /**
     * Validates whether the recipient information subtitle is displayed or not
     *
     * @return If recipient information subtitle is displayed then true else false
     * @Author: Balaji N
     */
    public boolean verify_recipient_information_subtitle() {
        HighlightElement(recipient_information_subtitle);
        return recipient_information_subtitle.isDisplayed();
    }

    /**
     * Enters the recipient first name on recipient information section in checkout page
     *
     * @param first_name The given first name to be entered
     * @Author: Balaji N
     */
    public void Enter_recipient_first_name(String first_name) {
        clickAndType(recipient_first_name_textbox, first_name);
    }

    /**
     * It retrieves the entered recipient first name on recipient information section in checkout page
     *
     * @return If recipient first name entered value is displayed on checkout page it returns the value of recipient first name entered value, otherwise it returns null string
     * @Author Balaji N
     */
    public @Nullable String get_entered_firstname_on_recipient_information_section() {
        HighlightElement(recipient_first_name_textbox);
        return recipient_first_name_textbox.getAttribute("value");
    }

    /**
     * Enters the recipient last name on recipient information section in checkout page
     *
     * @param last_name The given last name to be entered
     * @Author: Balaji N
     */
    public void Enter_recipient_last_name(String last_name) {
        clickAndType(recipient_last_name_textbox, last_name);
    }

    /**
     * It retrieves the entered recipient last name on recipient information section in checkout page
     *
     * @return If recipient last name entered value is displayed on checkout page it returns the value of recipient first name entered value, otherwise it returns null string
     * @Author Balaji N
     */
    public @Nullable String get_entered_lastname_on_recipient_information_section() {
        HighlightElement(recipient_last_name_textbox);
        return recipient_last_name_textbox.getAttribute("value");
    }

    /**
     * Enters the recipient phone number on recipient information section in checkout page
     *
     * @param phone_number The given phone number to be entered
     * @Author: Balaji N
     */
    public void Enter_recipient_phone_number(String phone_number) {
        clickAndType(recipient_phone_number_textbox, phone_number);
    }

    /**
     * It retrieves the entered recipient phone number on recipient information section in checkout page
     *
     * @return If recipient phone number entered value is displayed on checkout page it returns the value of recipient phone number entered value, otherwise it returns null string
     * @Author Balaji N
     */
    public @Nullable String get_entered_phone_number_on_recipient_information_section() {
        HighlightElement(recipient_phone_number_textbox);
        return recipient_phone_number_textbox.getAttribute("value");
    }

    public void Search_and_select_with_BusinessName(String businessName, String address) {
        // Enter the business name in the search box and wait for suggestions
        clickAndType(business_name_textbox, businessName);
        delayWithGivenTime(3000);

        // Loop through the autocomplete suggestions for business names
       /* for (WebElement businessSuggestion : list_of_businessName_googleAutoComplete) {
            if (businessSuggestion.getText().equalsIgnoreCase(businessName)) {
                // If the business name matches, check for matching address suggestions
      */
        for (WebElement addressSuggestion : list_of_business_address_street_Country_googleAutoComplete) {
            if (addressSuggestion.getText().contains(address)) {
                // If both name and address match, click on the address suggestion
                click(addressSuggestion);
                break; // Exit the method once the correct suggestion is clicked
            }
        }
           /* }
        }*/
    }


    /**
     * Validates whether the message popup is displayed or not
     *
     * @return If message popup is displayed then true else false
     * @Author: Balaji N
     */
    public boolean Verify_Message_Popup_isDisplay() {
        HighlightElement(display_message_popup_content);
        return display_message_popup_content.isDisplayed();
    }

    /**
     * Clicks the close icon on displayed message popup
     *
     * @Author: Balaji N
     */
    public void Click_on_outside_from_messagePopup() {
        jsClick(display_message_popup_close_button);
    }

    /**
     * It retrieves the displayed street address on recipient information section in checkout page
     *
     * @return If street address is displayed on checkout page it returns the displayed street address value, otherwise it returns null string
     * @Author: Balaji N
     */
    public @Nullable String get_displayed_street_address() {
        HighlightElement(recipient_street_address_textbox);
        return recipient_street_address_textbox.getAttribute("value");
    }

    public String get_selected_country() {
        HighlightElement(recipient_country_dropdown);
        Select select = new Select(recipient_country_dropdown);
        return select.getFirstSelectedOption().getText();
    }

    public String get_selected_state() {
        HighlightElement(recipient_state_dropdown);
        Select select = new Select(recipient_state_dropdown);
        return select.getFirstSelectedOption().getText();
    }

    public @Nullable String get_displayed_city() {
        HighlightElement(recipient_city_textbox);
        return recipient_city_textbox.getAttribute("value");
    }

    public @Nullable String get_displayed_zip_code() {
        HighlightElement(recipient_zip_code_textbox);
        return recipient_zip_code_textbox.getAttribute("value");
    }

    public void Enter_delivery_date(String delivery_date) {
        jsDatePicker(recipient_delivery_date_textbox, delivery_date);
    }

    public boolean Verify_Payment_Information_Tab_isDisplayed() {
        HighlightElement(payment_information_subtitle);
        return payment_information_subtitle.isDisplayed();
    }

    public void Select_Payment_Method(String payment_method) {
        dropDown(payment_method_dropdown, payment_method, "VisibleText");
    }

    public String get_selected_payment_method() {
        HighlightElement(payment_method_dropdown);
        Select select = new Select(payment_method_dropdown);
        return select.getFirstSelectedOption().getText();
    }

    public void Enter_Name_of_Card_on_Payment_Info(String name_of_card) {
        clickAndType(name_on_card_textbox, name_of_card);
    }

    public @Nullable String get_entered_name_of_card_on_Payment_Info() {
        HighlightElement(name_on_card_textbox);
        return name_on_card_textbox.getAttribute("value");
    }

    public void Enter_Card_Number_on_Payment_Info(String card_number) {
        fluentWait(card_number_textbox);
        clickAndType(card_number_textbox, card_number);
    }

    public @Nullable String get_entered_card_number_on_Payment_Info() {
        HighlightElement(card_number_textbox);
        return card_number_textbox.getAttribute("value");
    }

    public void Enter_expiry_date_on_Payment_Info(String expiry_date) {
        clickAndType(expiry_date_textbox, expiry_date);
    }

    public @Nullable String get_entered_expiry_date_on_Payment_Info() {
        HighlightElement(expiry_date_textbox);
        return expiry_date_textbox.getAttribute("value");
    }

    public void Enter_CVV_on_Payment_Info(String cvv) {
        clickAndType(ccvc_textbox, cvv);
    }

    public @Nullable String get_entered_CVV_on_Payment_Info() {
        HighlightElement(ccvc_textbox);
        return ccvc_textbox.getAttribute("value");
    }

    public boolean verify_billing_information_subtitle_isDisplayed() {
        HighlightElement(billing_information_subtitle);
        return billing_information_subtitle.isDisplayed();
    }

    public void enterBillingFirstName(String firstName) {
        clickAndType(billing_first_name_textbox, firstName);
    }

    public void enterBillingLastName(String lastName) {
        clickAndType(billing_last_name_textbox, lastName);
    }

    public void enterBillingPhoneNumber(String phoneNumber) {
        clickAndType(billing_phone_number_textbox, phoneNumber);
    }

    public void enterBillingStreetAddress(String streetAddress) {
        billing_street_address_textbox.click();
        delayWithGivenTime(1000);
        billing_street_address_textbox.clear();
        delayWithGivenTime(2000);
        // billing_street_address_textbox.sendKeys(streetAddress);
        clickAndType(billing_street_address_textbox, streetAddress);
    }

    public void selectBillingCountry(String country) {
        dropDown(billing_country_dropdown, country, "VisibleText");
    }

    public void selectBillingState(String state) {
        dropDown(billing_state_dropdown, state, "VisibleText");
    }

    public void enterBillingCity(String city) {
        clickAndType(billing_city_textbox, city);
    }

    public void enterBillingZipCode(String zipCode) {
        clickAndType(billing_zip_code_textbox, zipCode);
    }

    public String getBillingFirstName() {
        return billing_first_name_textbox.getAttribute("value");
    }

    public String getBillingLastName() {
        return billing_last_name_textbox.getAttribute("value");
    }

    public String getBillingPhoneNumber() {
        return billing_phone_number_textbox.getAttribute("value");
    }

    public String getBillingStreetAddress() {
        return billing_street_address_textbox.getAttribute("value");
    }

    public String getBillingCountry() {
        HighlightElement(billing_country_dropdown);
        Select select = new Select(billing_country_dropdown);
        return select.getFirstSelectedOption().getText();
    }

    public String getBillingState() {
        HighlightElement(billing_state_dropdown);
        Select select = new Select(billing_state_dropdown);
        return select.getFirstSelectedOption().getText();
    }

    public String getBillingCity() {
        return billing_city_textbox.getAttribute("value");
    }

    public String getBillingZipCode() {
        return billing_zip_code_textbox.getAttribute("value");
    }

    public void Click_continue_to_review_button() {
        click(continue_to_review_button);
    }

    public boolean Verify_Review_Information_Tab_isDisplayed() {
        HighlightElement(review_information_subtitle);
        return review_information_subtitle.isDisplayed();
    }

    public void Click_Agree_Substitution_Checkbox() {
        jsClick(substitution_policy_checkbox);
    }

    public void Click_Place_Your_Order_Button() {
        jsClick(place_order_button);
    }

    public boolean Verify_Order_Confirmation_Message() {
        HighlightElement(order_confirmation_heading);
        return order_confirmation_heading.isDisplayed();
    }

}
