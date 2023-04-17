import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangeElementStateTests extends BaseTest {

    @RepeatedTest(5)
    @Tag("UiPostTest")
    public void hidePictureDislikeTest() throws InterruptedException {
        RecommendationsPage recommendationsPage = homePage.getRecommendationPage();
        recommendationsPage.checkPage();

        assertTrue(
                recommendationsPage
                        .initPost()
                        .checkPostIsVisible()
                        .clickDislike()
                        .checkPostIsInvisible()
        );
    }
}
