import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class OkAuthTests {

    public final String authPageLink = "https://ok.ru/";
    private final String LOGIN = "";
    private final String PASSWD = "";

    @Test
    public void okAuthMainTest() {
        open(authPageLink);
        AuthPage authPage = new AuthPage(getWebDriver());

        authPage.inputLogin(LOGIN);
        authPage.inputPassword(PASSWD);

        HomePage homePage = authPage.submitAuth();
        assertTrue(homePage.isThisPageIsHomePage());
    }
}
