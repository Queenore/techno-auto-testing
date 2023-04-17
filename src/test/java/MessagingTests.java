import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class MessagingTests extends BaseTest {

    @Test
    @Tag("UiMessageTest")
    public void sendAndDeleteMessageTest() {
        FriendsPage friendsPage = homePage.getFriendsPage();
        MessagePage messagePage = friendsPage.getUserGridCard().getMessagePage();

        String message = RandomStringUtils.random(20, true, true);

        messagePage.sendMessage(message).deleteLastSendMessage();

        assertFalse(messagePage.isLastMessageVisible());
    }
}
