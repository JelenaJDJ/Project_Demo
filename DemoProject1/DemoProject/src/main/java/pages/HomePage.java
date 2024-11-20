package pages;

import base.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.Constants;
import org.openqa.selenium.support.Color;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    By OPEN_DIALOG_BUTTON = By.xpath("//button/span[text()='Open dialog']");
    By OPEN_WINDOW_BUTTON = By.xpath("//button/span[text()='Open window']");
    By PLEASE_CONFIRM_TITLE = By.xpath("//div//span[text()='Please confirm']");
    By POP_UP_QUESTION = By.xpath("//p[text()='Are you sure you want to continue?']");
    By NO_BUTTON = By.xpath("//kendo-dialog-actions//button[1]");
    By YES_BUTTON = By.xpath("//kendo-dialog-actions//button[2]");
    By X_BUTTON = By.xpath("//button[@icon='close']");
    By POP_UP_DIALOG = By.xpath("//div[@role='dialog']");
    By ABOUT_TITLE = By.xpath("//span[text()='About']");
    By ADDITIONAL_INFO_TEXT = By.xpath("//p[text()='Additional info']");
    By MINIMIZE_BUTTON = By.xpath("//button[@aria-label='Minimize']");
    By MAXIMIZE_BUTTON = By.xpath("//button[@aria-label='Maximize']");
    By CLOSE_BUTTON = By.xpath("//button[@aria-label='Close']");

    public HomePage clickOpenDialogButton() {
        click(OPEN_DIALOG_BUTTON);
        return this;
    }

    public HomePage clickOpenWindowButton() {
        click(OPEN_WINDOW_BUTTON);
        return this;
    }

    public HomePage clickMaximizeWindowButton() {
        click(MAXIMIZE_BUTTON);
        return this;
    }

    public HomePage clickCloseButton() {
        click(CLOSE_BUTTON);
        return this;
    }

    public HomePage verifyPleaseConfirmTitleOnPopUp() {
        waitForElementPresent(PLEASE_CONFIRM_TITLE, 6L);
        String title = getText(PLEASE_CONFIRM_TITLE);
        Assertions.assertEquals(title, Constants.PLEASE_CONFIRM_TITLE);
        return this;
    }

    public HomePage verifyPopUpQuestion() {
        waitForElementPresent(POP_UP_QUESTION, 6L);
        String text = getText(POP_UP_QUESTION);
        Assertions.assertEquals(text, Constants.POP_UP_QUESTION);
        return this;
    }

    public HomePage verifyNoButtonIsDisplayed() {
        verifyDisplayed(NO_BUTTON);
        return this;
    }

    public HomePage verifyYesButtonIsDisplayed() {
        verifyDisplayed(YES_BUTTON);
        return this;
    }

    public HomePage verifyXbuttonIsDisplayed() {
        verifyDisplayed(X_BUTTON);
        return this;
    }

    //This method can be used in Task1 test instead five smaller methods
    public HomePage verifyDialogElementsAreDisplayed() {
        verifyPleaseConfirmTitleOnPopUp()
                .verifyPopUpQuestion()
                .verifyNoButtonIsDisplayed()
                .verifyYesButtonIsDisplayed()
                .verifyXbuttonIsDisplayed();
        return this;
    }

    public HomePage verifyYesButtonBackgroundColor(String expectedColor) {
        String backgroundColor = findElement(YES_BUTTON).getCssValue("background-color");
        String actualColorHex = Color.fromString(backgroundColor).asHex();
        Assert.assertEquals(actualColorHex, expectedColor, " Background color of button doesn't match. ");
        return this;
    }

    public HomePage hoverOnTheXButton() {
        mouseHover(X_BUTTON);
        return this;
    }

    public HomePage closeWithEnterKey() {
        waitForElementPresent(X_BUTTON, 4L);
        WebElement popUpDilaog = findElement(X_BUTTON);
        popUpDilaog.sendKeys(Keys.ENTER);
        return this;
    }

    public HomePage verifyHomePageIsDisplayed() {
        verifyDisplayed(OPEN_DIALOG_BUTTON);
        verifyDisplayed(OPEN_WINDOW_BUTTON);
        return this;
    }

    public HomePage verifyAboutTitleOnPopUp() {
        waitForElementPresent(ABOUT_TITLE, 4L);
        String title = getText(ABOUT_TITLE);
        Assertions.assertEquals(Constants.ABOUT_TITLE, title, " Titles do not match! ");
        return this;
    }

    public HomePage verifyAdditionalInfoTextOnPopUp() {
        waitForElementPresent(ADDITIONAL_INFO_TEXT, 6L);
        String text = getText(ADDITIONAL_INFO_TEXT);
        Assertions.assertEquals(Constants.ABOUT_POP_UP_TEXT, text, "Texts do not match!");
        return this;
    }

    public HomePage verifyMinimizeButtonOnAboutPopUp() {
        verifyDisplayed(MINIMIZE_BUTTON);
        String actualText = findElement(MINIMIZE_BUTTON).getAttribute("title");
        Assert.assertEquals(Constants.MINIMIZE_TEXT, actualText, " Actual text doesn't match with expected");
        return this;
    }

    public HomePage verifyMaximizeButtonOnAboutPopUp() {
        verifyDisplayed(MAXIMIZE_BUTTON);
        String actualText = findElement(MAXIMIZE_BUTTON).getAttribute("title");
        Assert.assertEquals(Constants.MAXIMIZE_TEXT, actualText, " Actual text doesn't match with expected ");
        return this;
    }

    public HomePage verifyXButtonOnAboutPopUp() {
        verifyDisplayed(CLOSE_BUTTON);
        String actualText = findElement(CLOSE_BUTTON).getAttribute("title");
        Assert.assertEquals(Constants.CLOSE_TEXT, actualText, " Actual text doesn't match with expected ");
        return this;
    }

    public HomePage verifyMaximizeButtonIsNotDisplayed() {
        verifyNotDisplayed(MAXIMIZE_BUTTON);
        return this;
    }

}
