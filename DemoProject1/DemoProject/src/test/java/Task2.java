import base.BasePage;
import base.BaseTest;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import pages.HomePage;

public class Task2 extends BaseTest {

    @Description("This test verify that Home page is opened, verified data from Window pop up, verified maximized window, verified not visible window and verify closed window")
    @Test
    public void openWindowPopUp() {
        new BasePage(driver).loadUrl("task1_2_URL");
        HomePage homePage = new HomePage(driver);
        homePage.clickOpenWindowButton()
                .verifyAboutTitleOnPopUp()
                .verifyMinimizeButtonOnAboutPopUp()
                .verifyMaximizeButtonOnAboutPopUp()
                .verifyXButtonOnAboutPopUp()
                .verifyAdditionalInfoTextOnPopUp()
                .clickMaximizeWindowButton()
                .verifyMaximizeButtonIsNotDisplayed()
                .clickCloseButton()
                .verifyHomePageIsDisplayed();


    }
}
