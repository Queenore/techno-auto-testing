import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RadioGroupTests extends BaseTest {

    @Test
    @Tag("UiSettingsTest")
    public void checkRadioGroupRequiredBehaviourTest() {
        SettingsPage settingsPage = homePage.getSettingsPage();
        settingsPage.goToPublicitySettings();

        settingsPage.clickRadioButton(2);

        assertAll(
                () -> assertFalse(settingsPage.getRadioButton(1).isSelected()),
                () -> assertTrue(settingsPage.getRadioButton(2).isSelected()),
                () -> assertFalse(settingsPage.getRadioButton(3).isSelected())
        );

        settingsPage.clickRadioButton(1);
    }
}
