import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.notNullValue;


public class OkAuthTests {

    private final String authPageLink = "https://ok.ru/";
    private final String LOGIN = "botS23AT25";
    private final String PASSWD = "autotests2023";

    private final String ACCOUNT_NAME = "//a[@class='nav-side_i  __with-ic __with-new-icons']";
    private final String DATE_NUMBER = "//*[@class='feed_date']";

    @Test
    public void okAuthMainTest() {
        open(authPageLink);
        AuthPage authPage = new AuthPage();

        authPage.inputLogin(LOGIN);
        authPage.inputPassword(PASSWD);

        HomePage homePage = authPage.submitAuth();
    }

    @Test
    public void firstHamcrestTest() {
        String name = "BOTS23at25 bOtS23aT25";

        String okName = $$(byXpath(ACCOUNT_NAME))
                .get(0)
                .find(By.cssSelector("div"))
                .getText();
        assertThat(name, equalToIgnoringCase(okName));
    }

    @Test
    public void secondHamcrestTest() {
        String dateNumber = $$(byXpath(DATE_NUMBER)).get(0).getText().split("")[0];
        assertThat(dateNumber, notNullValue());
    }

    @Test
    public void customTest() {
        HomePage homePage = new HomePage();
        SelenideElement element = homePage.getInvisibleModeToggle();
        assertThat(element, CheckboxMatcher.isCheckboxChecked());
    }
}
