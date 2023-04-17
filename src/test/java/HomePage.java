import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class HomePage extends BasePage {

    private static final String NOTE = "//a[@class='pf-head_itx_a']";
    private static final String FEED_FILTER ="//div[@class='filter filter__nowrap h-mod']" ;
    private final String INVISIBLE_MODE_TOGGLE = "//*[@id='invisibleToggler']";
    private final String FRIENDS = "//*[@aria-label='Друзья']";
    private final String RECOMMENDATIONS = "//*[@aria-label='Рекомендации']";
    private final String USER_DROPDOWN_MENU = "//*[@aria-controls='user-dropdown-menu']";
    private final String SETTINGS_BUTTON = "//*[@class='u-menu']/li[1]//span[@class='u-menu_tx ellip-i lp']" ;

    @Override
    public void checkPage() {
        $(byClassName("photo")).shouldBe(Condition.visible);
        $(byXpath(NOTE)).shouldBe(Condition.visible);
        $(byXpath(FEED_FILTER)).shouldBe(Condition.visible);
    }

    public SelenideElement getInvisibleModeToggle() {
        return $(byXpath(INVISIBLE_MODE_TOGGLE)).shouldBe(Condition.visible);
    }

    public SettingsPage getSettingsPage() {
        $(byXpath(USER_DROPDOWN_MENU)).shouldBe(Condition.visible).click();
        $(byXpath(SETTINGS_BUTTON)).shouldBe(Condition.visible).click();
        return new SettingsPage();
    }

    public FriendsPage getFriendsPage() {
        $(byXpath(FRIENDS)).shouldBe(Condition.visible).click();
        return new FriendsPage();
    }

    public RecommendationsPage getRecommendationPage() {
        $(byXpath(RECOMMENDATIONS)).shouldBe(Condition.visible).click();
        return new RecommendationsPage();
    }

    public OkPurchasePage getOkPurchasePage() {
        $(By.xpath("//div[contains(text(), 'Купить ОКи')]")).shouldBe(Condition.visible).click();
        return new OkPurchasePage();
    }
}
