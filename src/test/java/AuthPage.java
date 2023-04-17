import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.$;

public class AuthPage extends BasePage {

    private static final SelenideElement LOGIN_FORM = $(By.className("js-login-form"));

    @Override
    public void checkPage() {
        LOGIN_FORM.shouldBe(Condition.visible);
    }

    void inputLogin(String login) {
        LOGIN_FORM.$(byName("st.email")).shouldBe(Condition.visible).val(login);
    }

    void inputPassword(String passwd) {
        LOGIN_FORM.$(byName("st.password")).shouldBe(Condition.visible).val(passwd);
    }

    HomePage submitAuth() {
        LOGIN_FORM.$(byValue("Войти в Одноклассники")).shouldBe(Condition.visible).click();
        return new HomePage();
    }
}