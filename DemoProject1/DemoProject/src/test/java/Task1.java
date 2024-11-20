import base.BasePage;
import base.BaseTest;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import pages.HomePage;

public class Task1 extends BaseTest {


    @Description("This test verify that Home page is opened, Dialog elements are presented, background color of Yes button, hovered on X button, and verify that dialog is closed on Enter key")
    @Test
    public void dialogElements() {
        new BasePage(driver).loadUrl("task1_2_URL");
        HomePage homePage = new HomePage(driver);
        homePage.clickOpenDialogButton()
                .verifyPleaseConfirmTitleOnPopUp()
                .verifyPopUpQuestion()
                .verifyXbuttonIsDisplayed()
                .verifyNoButtonIsDisplayed()
                .verifyYesButtonIsDisplayed()
                .verifyYesButtonBackgroundColor("#ff6358")
                .hoverOnTheXButton()
                .closeWithEnterKey()
                .verifyHomePageIsDisplayed();

    }

}
