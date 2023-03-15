import com.codeborne.selenide.Driver;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.$;

public class AuthPage {
    private final SelenideElement loginForm = $(By.className("js-login-form"));
    private final WebDriver driver;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
    }

    void inputLogin(String login) {
        this.loginForm.find(byName("st.email")).val(login);
    }

    void inputPassword(String passwd) {
        this.loginForm.find(byName("st.password")).val(passwd);
    }

    HomePage submitAuth() {
        this.loginForm.find(byValue("Войти в Одноклассники")).click();
        return new HomePage(driver);
    }
}