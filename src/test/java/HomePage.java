import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isThisPageIsHomePage() {
        return $(byClassName("photo")).should(exist).exists() &&
                $(byXpath("//a[@class='pf-head_itx_a']")).should(exist).exists() &&
                $(byXpath("//div[@class='filter filter__nowrap h-mod']")).should(exist).exists();
    }
}
