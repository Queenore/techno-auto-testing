import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class SettingsPage {

    private final WebDriver driver;

    public SettingsPage(WebDriver driver) {
        this.driver = driver;
    }

    public PasswordPage getPasswordPage() {
        $(byXpath("//*[@hrefattrs=\"st.cmd=userConfigChangePassword\"]"))
                .shouldBe(Condition.visible).click();
        return new PasswordPage(driver);
    }
    public void goToPublicitySettings() {
        $(byXpath("//div[contains(@class,'tico null')][contains(text(),'Публичность')]"))
                .shouldBe(Condition.visible).parent().click();
    }

    public void clickFirstRadioButton() {
        getFirstRadioButton().click();
    }

    public void clickSecondRadioButton() {
        getSecondRadioButton().click();
    }

    public SelenideElement getFirstRadioButton() {
        return $(byXpath("//tbody/tr[1]/td[2]/input")).shouldBe(Condition.visible);
    }

    public SelenideElement getSecondRadioButton() {
        return $(byXpath("//tbody/tr[1]/td[3]/input")).shouldBe(Condition.visible);
    }

    public SelenideElement getThirdRadioButton() {
        return $(byXpath("//tbody/tr[1]/td[4]/input")).shouldBe(Condition.visible);
    }
}
