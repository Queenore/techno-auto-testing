import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MessagePage extends BasePage {

    private static final String MESSENGER_MAIN = "//div[@class='messenger_main']";
    private static final String INPUT_MSG_FIELD = "//msg-input[@placeholder='Напишите сообщение...']";
    private final String MSG_FIELD = "//div[@class='js-lottie-observer']//div";
    private final String SEND_MSG_BUTTON = "//div[@class='buttons __right']//msg-button[@title='Отправить']";
    private final String MSG_MODIFY_BUTTON = "//msg-icon[@icon='menu']//*[name()='svg']";
    private final String DELETE_BUTTON = "//msg-tico[@icon='delete']//span[@class='tico_tx']";
    private final String CONFIRM_DELETE_BUTTON = "//msg-button[@data-tsid='confirm-primary']";

    private String lastSendingMessage = null;

    @Override
    public void checkPage() {
        $(By.xpath(MESSENGER_MAIN)).shouldBe(Condition.visible);
        $(By.xpath(INPUT_MSG_FIELD)).shouldBe(Condition.visible);
    }


    public MessagePage sendMessage(String message) {
        lastSendingMessage = message;
        $(By.xpath(MSG_FIELD)).should(Condition.visible).sendKeys(lastSendingMessage);
        $(By.xpath(SEND_MSG_BUTTON)).should(Condition.visible).click();
        return this;
    }

    public void deleteLastSendMessage() {
        $(By.xpath("//span[@data-tsid='message_text']//span[contains(text(),'" + lastSendingMessage + "')]"))
                .should(Condition.visible).hover();
        $(By.xpath(MSG_MODIFY_BUTTON)).should(Condition.visible).click();
        $(By.xpath(DELETE_BUTTON)).should(Condition.visible).click();
        $(By.xpath(CONFIRM_DELETE_BUTTON)).should(Condition.visible).click();
    }

    public boolean isLastMessageVisible() {
        SelenideElement msg = $(By.xpath("//div[@class=\"group\"]//span[contains(text(), '" + lastSendingMessage + "')]"));
        msg.shouldNotBe(Condition.visible.because("сообщение удалено"));
        return msg.is(Condition.visible);
    }
}
