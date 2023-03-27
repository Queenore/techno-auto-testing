import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class MessagePage {

    private final WebDriver driver;
    private String lastMessage = null;

    public MessagePage(WebDriver driver) {
        this.driver = driver;
    }

    public MessagePage sendMessage(String message) {
        lastMessage = message;
        $(By.xpath("//div[@class='js-lottie-observer']//div")).should(Condition.visible).sendKeys(message);
        $(By.xpath("//div[@class='buttons __right']//msg-button[@title='Отправить']"))
                .should(Condition.visible).click();
        return this;
    }

    public void deleteLastSendMessage() {
        $(By.xpath("//span[@data-tsid='message_text']//span[contains(text(),'" + lastMessage + "')]"))
                .should(Condition.visible).hover();
        $(By.xpath("//msg-icon[@icon='menu']//*[name()='svg']")).should(Condition.visible).click();
        $(By.xpath("//msg-tico[@icon='delete']//span[@class='tico_tx']")).should(Condition.visible).click();
        $(By.xpath("//msg-button[@data-tsid='confirm-primary']")).should(Condition.visible).click();
    }
}
