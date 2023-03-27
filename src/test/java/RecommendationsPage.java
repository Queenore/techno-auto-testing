import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class RecommendationsPage implements LoadablePage {

    private final WebDriver driver;
    private SelenideElement post = null;

    public RecommendationsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void checkPage() {
        $(By.xpath("//div[@class='dicovery_col js-discovery-first-col']"))
                .shouldBe(Condition.visible);
        $(By.xpath("//div[@class='dicovery_col js-discovery-second-col']"))
                .shouldBe(Condition.visible);
    }

    public RecommendationsPage initPost() {
        post = $(By.xpath("//*[@data-module=\"LogSeen\"]")).shouldBe(Condition.visible);
        return this;
    }

    public RecommendationsPage checkPostIsVisible() {
        post.$(By.xpath(".//*[@class='hook delete-stub']")).shouldBe(Condition.hidden);
        return this;
    }

    public RecommendationsPage clickDislike() {
        post.$(By.xpath(".//*[@class=\"i ic discovery-item_ac_ic ic_dislike\"]")).shouldBe(Condition.visible).click();
        return this;
    }

    public RecommendationsPage checkPostIsInvisible() throws InterruptedException {
        post.$(By.xpath(".//*[@class='hook delete-stub']")).shouldNotBe(Condition.hidden);
        Thread.sleep(1000);
        return this;
    }
}
