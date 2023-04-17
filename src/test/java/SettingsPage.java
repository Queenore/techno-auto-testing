import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class SettingsPage extends BasePage {

    private static final String MAIN_SETTINGS_AREA = "//*[@class='portlet']";
    private final String PASSWD_PAGE_BUTTON = "//*[@hrefattrs=\"st.cmd=userConfigChangePassword\"]";
    private final String PUBLICITY_SETTINGS_BUTTON = "//div[contains(@class,'tico null')][contains(text(),'Публичность')]";

    @Override
    public void checkPage() {
        $(By.xpath(MAIN_SETTINGS_AREA)).shouldBe(Condition.visible);
    }

    public PasswordPage getPasswordPage() {
        $(byXpath(PASSWD_PAGE_BUTTON)).shouldBe(Condition.visible).click();
        return new PasswordPage();
    }

    public void goToPublicitySettings() {
        $(byXpath(PUBLICITY_SETTINGS_BUTTON)).shouldBe(Condition.visible).parent().click();
    }

    public void clickRadioButton(Integer pos) {
        getRadioButton(pos).click();
    }

    public SelenideElement getRadioButton(Integer pos) {
        return $(byXpath("//tbody/tr[1]/td[" + ++pos + "]/input")).shouldBe(Condition.visible);
    }
}
