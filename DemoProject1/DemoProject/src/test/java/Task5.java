import base.BasePage;
import base.BaseTest;
import org.testng.annotations.Test;
import pages.SportPage;

public class Task5 extends BaseTest {

    @Test
    public void selectSport() {
        new BasePage(driver).loadUrl("task4_5_URL");
    }
}
