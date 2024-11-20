package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigLoader;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void loadUrl(String url) {
        driver.get(ConfigLoader.getInstance().getUrl(url));
    }

    public WebElement findElement(By locator) {
        try {
            return driver.findElement(locator);
        } catch (Exception e) {
            System.out.println("Error finding element: " + e.getMessage());
            return null;
        }
    }

    public List<WebElement> findElements(By locator) {
        try {
            return driver.findElements(locator);
        } catch (Exception e) {
            System.out.println("Error finding elements: " + e.getMessage());
            return null;
        }
    }

    public void verifyDisplayed(By locator) {
        waitForElementPresent(locator, 5L);
        verifyDisplayed(locator, 3, 1);

    }

    public void verifyDisplayed(By locator, int retryCount, int delay) {
        WebElement element = driver.findElement(locator);
        for (int attempt = 0; attempt < retryCount; attempt++) {
            try {
                assertTrue(element.isDisplayed(), "Element is not displayed: " + locator);
                return;
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException caught. Retrying...");
                sleep(delay);
                element = driver.findElement(locator);
            }
        }
        throw new RuntimeException("Failed to click the element after " + retryCount + " attempts.");
    }

    public void verifyNotDisplayed(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            assertFalse(element.isDisplayed(), "Element is visible: " + locator);
        } catch (NoSuchElementException e) {
            assertTrue(true);
        } catch (StaleElementReferenceException e) {
            //element is stale try to re-find it
            try {
                WebElement element = driver.findElement(locator);
                assertFalse(element.isDisplayed(), "Element is visible: " + locator);
            } catch (NoSuchElementException ex) {
                //element is not found, hence not displayed
                assertTrue(true);
            }
        }
    }

    // Wait for an element to be visible
    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForClickable(By button) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.elementToBeClickable(button));
    }

    // Click on a WebElement
    protected void click(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    public void click(By locator, int retryCount, int delay) {
        WebElement element = driver.findElement(locator);
        for (int attempt = 0; attempt < retryCount; attempt++) {
            try {
                element.click();
                return;
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException caught. Retrying...");
                sleep(delay);
                element = driver.findElement(locator);
            }
        }
        throw new RuntimeException("Failed to click the element after " + retryCount + " attempts.");
    }

    public void click(By button) {
        click(button, 3, 1);
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    public void mouseHover(By button) {
        WebElement el = driver.findElement(button);
        Actions actions = new Actions(driver);
        actions.moveToElement(el).perform();
    }

    public String getTextFromElement(By locator) {
        WebElement element = driver.findElement(locator);
        return element.getText();
    }


    public String getText(By locator) {
        return getText(locator, 3, 1);
    }

    public String getText(By locator, int retryCount, int delay) {
        WebElement element = driver.findElement(locator);
        for (int attempt = 0; attempt < retryCount; attempt++) {
            try {
                new WebDriverWait(driver, Duration.ofSeconds(5L)).until(new TextToBeNonEmpty(locator));
                return element.getText();
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException caught. Retrying...");
                sleep(delay);
                element = driver.findElement(locator);
            }
        }
        throw new RuntimeException("Failed to fetch the element after " + retryCount + " attempts.");
    }

    private static class TextToBeNonEmpty implements ExpectedCondition<Boolean> {
        private final By locator;

        public TextToBeNonEmpty(By locator) {

            this.locator = locator;
        }

        @Override
        public Boolean apply(WebDriver driver) {
            WebElement element = driver.findElement(locator);
            return !element.getText().trim().isEmpty();
        }
    }

    public void type(By fieldName, String value) {
        driver.findElement(fieldName).sendKeys(value);
    }

    public void clear(By fieldName) {
        driver.findElement(fieldName).clear();
    }


    public void waitForElementPresent(By locator, Long seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitToDisappear(By locator, Long seconds) {
        WebElement element = driver.findElement(locator);
        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.invisibilityOf(element));
    }

    public String getAttributeValue(By locator, String attribute) {
        return driver.findElement(locator).getAttribute(attribute);
    }


    // Get the title of the current page
    public String getPageTitle() {
        return driver.getTitle();
    }

}

