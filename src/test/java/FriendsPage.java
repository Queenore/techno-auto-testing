import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FriendsPage extends BasePage {

    private SelenideElement userGridCard = null;

    private final String FRIENDS_AREA = "//*[@class='portlet']";
    private final String GRID_CARD = "//*[@class='user-grid-card __s']";
    private final String SEND_MSG = ".//*[@title='Написать сообщение']";

    @Override
    public void checkPage() {
        $(By.xpath(FRIENDS_AREA)).shouldBe(Condition.visible);
    }

    public FriendsPage getUserGridCard() {
        userGridCard = $(By.xpath(GRID_CARD));
        userGridCard.shouldBe(Condition.visible.because("друг есть!"));
        return this;
    }

    public MessagePage getMessagePage() {
        userGridCard.$(By.xpath(SEND_MSG)).click();
        return new MessagePage();
    }
}
