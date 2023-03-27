import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class PasswordPage {

    private final WebDriver driver;

    public PasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public PasswordPage setOldPassword(String oldPasswd) {
        $(By.xpath("//input[@id='field_oldPassword']")).shouldBe(Condition.interactable).val(oldPasswd);
        return this;
    }

    public PasswordPage setNewPassword(String newPasswd) {
        $(By.xpath("//input[@id='field_newPassword']")).shouldBe(Condition.interactable).val(newPasswd);
        $(By.xpath("//input[@id='field_retypePassword']")).shouldBe(Condition.interactable).val(newPasswd);
        return this;
    }

    public void submitPasswordChange() {
        $(By.xpath("//input[@value='Сохранить']")).shouldBe(Condition.interactable).click();
    }
}
