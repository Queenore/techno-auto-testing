import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class OkPurchasePage extends BasePage {

    private static final String PAYMENT_FRAME = "//*[@class='modal-new_payment-frame']";
    private static final String CARDS_FRAME = "//*[@name='cardsFrame']" ;
    private final String SUBMIT_PURCHASE_BUTTON = "//input[@value='Оплатить']" ;

    static {
        getWebDriver().switchTo().frame($(By.xpath(PAYMENT_FRAME)));
        getWebDriver().switchTo().frame($(By.xpath(CARDS_FRAME)));
    }

    public boolean isSubmitPaymentButtonVisible() {
        SelenideElement purchaseButton = $(By.xpath(SUBMIT_PURCHASE_BUTTON));
        purchaseButton.shouldBe(Condition.visible);
        return purchaseButton.is(Condition.visible);
    }
}
