import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimingTests extends BaseTest {

    @Test
    @Tag("UiTimingTest")
    public void timingTest() {
        assertTimeout(ofMillis(3000L),
                () -> {
                    OkPurchasePage okPurchasePage = homePage.getOkPurchasePage();
                    assertTrue(okPurchasePage.isSubmitPaymentButtonVisible());
                }
        );
    }
}
