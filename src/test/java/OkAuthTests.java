import com.codeborne.selenide.SelenideElement;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class OkAuthTests {

    public final String authPageLink = "https://ok.ru/";
    private final String LOGIN = "89215787637";
    private final String PASSWD = "dNb-KD7-7dZ-vR4";

    @Test
    public void okAuthMainTest() {
        open(authPageLink);
        AuthPage authPage = new AuthPage(getWebDriver());

        authPage.inputLogin(LOGIN);
        authPage.inputPassword(PASSWD);

        HomePage homePage = authPage.submitAuth();
        assertTrue(homePage.isThisPageIsHomePage());
    }

    @Test
    public void firstHamcrestTest() {
        String name = "андрей чешев";

        String okName = $$(byXpath("//a[@class='nav-side_i  __with-ic __with-new-icons']"))
                .get(0)
                .find(By.cssSelector("div")).getText();
        assertThat(name, equalToIgnoringCase(okName));
    }

    @Test
    public void secondHamcrestTest() {
        String dateNumber = $$(byXpath("//*[@class=\"feed_date\"]"))
                .get(0).getText().split("")[0];
        assertThat(dateNumber, notNullValue());
    }

    public static class IsCheckboxChecked extends TypeSafeMatcher<SelenideElement> {

        public void describeTo(Description description) {
            description.appendText("checked checkbox");
        }

        public static Matcher<SelenideElement> isCheckboxChecked() {
            return new IsCheckboxChecked();
        }

        @Override
        protected boolean matchesSafely(SelenideElement element) {
            return Objects.equals(element.getValue(), "on");
        }
    }

    @Test
    public void customTest() {
        // проблема с тем, что value=="on" присутствует как у включенного, так и у выключенного тоггла
        // то есть у выключенного элемента атрибут как бы скрывается
        SelenideElement element = $(byXpath("//*[@id=\"invisibleToggler\"]"));
        assertThat(element, IsCheckboxChecked.isCheckboxChecked());
    }
}
