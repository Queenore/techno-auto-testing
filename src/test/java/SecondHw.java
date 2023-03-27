import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.driver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecondHw {

    public static final String authPageLink = "https://ok.ru/";
    public static final String homePageLink = "https://ok.ru/feed/";
    private static final String LOGIN = "botS23AT25";
    private static final String PASSWD = "autotests2023";
    private static HomePage homePage = null;

    @BeforeAll
    public static void okAuth() {
        open(authPageLink);
        AuthPage authPage = new AuthPage(getWebDriver());

        authPage.inputLogin(LOGIN);
        authPage.inputPassword(PASSWD);

        homePage = authPage.submitAuth();
        assertTrue(homePage.isThisPageIsHomePage());
    }

    @BeforeEach
    public void goHome() {
        open(homePageLink);
    }

    @Test
    @Tag("UiSettingsTest")
    public void checkRadioGroupRequiredBehaviourTest() {
        SettingsPage settingsPage = homePage.getSettingsPage();
        settingsPage.goToPublicitySettings();

        SelenideElement firstRadioButton = settingsPage.getFirstRadioButton();
        SelenideElement secondRadioButton = settingsPage.getSecondRadioButton();
        SelenideElement thirdRadioButton = settingsPage.getThirdRadioButton();

        settingsPage.clickSecondRadioButton();
        assertAll(
                () -> assertFalse(firstRadioButton.isSelected()),
                () -> assertTrue(secondRadioButton.isSelected()),
                () -> assertFalse(thirdRadioButton.isSelected())
        );
        settingsPage.clickFirstRadioButton();
    }

    @Test
    @Tag("UiMessageTest")
    public void sendAndDeleteMessageTest() {
        FriendsPage friendsPage = homePage.getFriendsPage();
        MessagePage messagePage = friendsPage.getUserGridCard().getMessagePage();

        String message = RandomStringUtils.random(20, true, true);

        messagePage.sendMessage(message).deleteLastSendMessage();

        $(By.xpath("//div[@class=\"group\"]//span[contains(text(), '" + message + "')]"))
                .shouldNotBe(Condition.visible.because("сообщение удалено"));
    }

    @Test
    @Tag("UiTimingTest")
    public void timingTest() {
        assertTimeout(ofMillis(3000L),
                () -> {
                    $(By.xpath("//div[contains(text(), 'Купить ОКи')]")).shouldBe(Condition.visible).click();
                    driver().switchTo().frame($(By.xpath("//*[@class=\"modal-new_payment-frame\"]")));
                    driver().switchTo().frame($(By.xpath("//*[@name=\"cardsFrame\"]")));
                    $(By.xpath("//input[@value='Оплатить']")).shouldBe(Condition.visible);
                });
    }

    @ParameterizedTest(name = "check password validation")
    @ValueSource(strings = {"qwe", "1", "", "4d12f", "фыва3йьй4"})
    @Tag("ValidationTest")
    public void passwdValidationTest(String newPasswd) {
        SettingsPage settingsPage = homePage.getSettingsPage();
        PasswordPage passwordPage = settingsPage.getPasswordPage();

        passwordPage.setOldPassword(PASSWD).setNewPassword(newPasswd).submitPasswordChange();

        $(By.xpath("//div[@class='iblock __ok __green-icon user-settings_info_block mt-6x']"))
                .shouldNotBe(Condition.exist);
        assertNotEquals($(By.xpath("//div[@class='iblock __warn user-settings_info_block __nomargin']//span"))
                .shouldBe(Condition.exist).getText(), "Пароль изменен.");
    }

    @RepeatedTest(5)
    public void hidePictureDislikeTest() throws InterruptedException {
        RecommendationsPage recommendationsPage = homePage.getRecommendationPage();
        recommendationsPage.checkPage();
        recommendationsPage.initPost().checkPostIsVisible().clickDislike().checkPostIsInvisible();
    }

}
