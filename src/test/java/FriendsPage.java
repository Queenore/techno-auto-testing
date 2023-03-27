import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class FriendsPage {

    private final WebDriver driver;
    private SelenideElement userGridCard = null;

    public FriendsPage(WebDriver driver) {
        this.driver = driver;
    }

    public FriendsPage getUserGridCard() {
        userGridCard = $(new By.ByXPath("//*[@class=\"user-grid-card __s\"]"));
        userGridCard.shouldBe(Condition.visible.because("друг есть!"));
        return this;
    }

    public MessagePage getMessagePage() {
        userGridCard.$(By.xpath(".//*[@title=\"Написать сообщение\"]")).click();
        return new MessagePage(driver);
    }
}
