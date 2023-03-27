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

    public SettingsPage getSettingsPage() {
        $(byXpath("//*[@aria-controls=\"user-dropdown-menu\"]")).click();
        $(byXpath("//*[@class=\"u-menu\"]/li[1]//span[@class='u-menu_tx ellip-i lp']")).click();
        return new SettingsPage(driver);
    }

    public FriendsPage getFriendsPage() {
        $(byXpath("//*[@aria-label=\"Друзья\"]")).click();
        return new FriendsPage(driver);
    }

    public RecommendationsPage getRecommendationPage() {
        $(byXpath("//*[@aria-label=\"Рекомендации\"]")).click();
        return new RecommendationsPage(driver);
    }
}
