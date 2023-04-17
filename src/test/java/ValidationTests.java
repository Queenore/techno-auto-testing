import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ValidationTests extends BaseTest {

    @ParameterizedTest(name = "password validation")
    @ValueSource(strings = {"qwe", "1", "", "4d12f", "фыва3йьй4"})
    @Tag("ValidationTest")
    public void passwdValidationTest(String newPasswd) {
        SettingsPage settingsPage = homePage.getSettingsPage();
        PasswordPage passwordPage = settingsPage.getPasswordPage();

        passwordPage.setOldPassword(PASSWD).setNewPassword(newPasswd).submitPasswordChange();

        assertNotEquals(passwordPage.getInfoAfterChangingPasswd(), "Пароль изменен.");
    }
}
