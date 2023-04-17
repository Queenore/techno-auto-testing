import com.codeborne.selenide.SelenideElement;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CheckboxMatcher extends TypeSafeMatcher<SelenideElement> {

    public void describeTo(Description description) {
        description.appendText("checked checkbox");
    }

    public static Matcher<SelenideElement> isCheckboxChecked() {
        return new CheckboxMatcher();
    }

    @Override
    protected boolean matchesSafely(SelenideElement element) {
        return !element.isSelected();
    }
}