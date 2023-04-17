import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class RecommendationsPage extends BasePage {

    private static final String FIRST_COLUMN = "//div[@class='dicovery_col js-discovery-first-col']";
    private static final String SECOND_COLUMN = "//div[@class='dicovery_col js-discovery-second-col']";
    private final String FIRST_POST = "//*[@data-module='LogSeen']";
    private final String POST_WINDOW = ".//*[@class='hook delete-stub']";
    private final String POST_DISLIKE = ".//*[@class='i ic discovery-item_ac_ic ic_dislike']";

    private SelenideElement post = null;

    @Override
    public void checkPage() {
        $(By.xpath(FIRST_COLUMN)).shouldBe(Condition.visible);
        $(By.xpath(SECOND_COLUMN)).shouldBe(Condition.visible);
    }

    public RecommendationsPage initPost() {
        post = $(By.xpath(FIRST_POST)).shouldBe(Condition.visible);
        return this;
    }

    public RecommendationsPage checkPostIsVisible() {
        post.$(By.xpath(POST_WINDOW)).shouldBe(Condition.hidden);
        return this;
    }

    public RecommendationsPage clickDislike() {
        post.$(By.xpath(POST_DISLIKE)).shouldBe(Condition.visible).click();
        return this;
    }

    public boolean checkPostIsInvisible() {
        post.$(By.xpath(POST_WINDOW)).shouldNotBe(Condition.hidden);
        return !post.$(By.xpath(POST_WINDOW)).is(Condition.hidden);
    }
}
