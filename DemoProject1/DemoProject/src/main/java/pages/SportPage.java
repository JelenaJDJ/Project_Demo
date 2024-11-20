package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SportPage extends BasePage {

    public SportPage(WebDriver driver) {
        super(driver);
    }

    By AUTOCOMPLETE_INPUT_FIELD = By.xpath("//input[@placeholder='Your favorite sport']");
    By AUTOCOMPLETE_CLOSE_BUTTON = By.xpath("//kendo-autocomplete/span");

    public SportPage clickOnAutocompleteField() {
        waitForElementPresent(AUTOCOMPLETE_INPUT_FIELD, 4L);
        click(AUTOCOMPLETE_INPUT_FIELD);
        return this;
    }

    public SportPage chooseFavoriteSport(String sport) {
        type(AUTOCOMPLETE_INPUT_FIELD, sport);
        WebElement textField = findElement(AUTOCOMPLETE_INPUT_FIELD);
        textField.sendKeys(Keys.ENTER);
        return this;
    }

    public SportPage verifyTextFromAutocompleteField() {
        waitForElementPresent(AUTOCOMPLETE_INPUT_FIELD, 6L);
        getTextFromElement(AUTOCOMPLETE_INPUT_FIELD);
        System.out.println(getTextFromElement(AUTOCOMPLETE_INPUT_FIELD));
        Assert.assertEquals(getTextFromElement(AUTOCOMPLETE_INPUT_FIELD).toLowerCase(), "tennis".toLowerCase());
        return this;
    }

}
