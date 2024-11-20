package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class EmployeesPage extends BasePage {
    public EmployeesPage(WebDriver driver) {
        super(driver);
    }

    By COUNTRY_MENU = By.xpath("//a[@title='Country Column Menu']");
    //  By FILTER_DROPDOWN = By.xpath("//kendo-grid-columnmenu-item//div[text()=' Filter ']");
    By FILTER_DROPDOWN = By.xpath("//kendo-grid-columnmenu-filter//div[text()=' Filter ']");
    By COUNTRY_MENU_TEXT_FIELD = By.xpath("//div[@class='k-filter-menu-container']//input");
    By FILTER_BUTTON = By.xpath("//button[text()='Filter']");
    By DATA_TABLE_LIST = By.xpath("//div[@aria-label='Data table']//tr");
    By STATUS_COLUMN_MENU = By.xpath("//a[@title='Status Column Menu']");
    By IS_TRUE_RADIO_BUTTON = By.xpath("(//kendo-grid-boolean-filter-menu//kendo-radiobutton)[1]");
    By EXPORT_TO_EXCEL_BUTTON = By.xpath("//kendo-grid-toolbar//button[1]");

    static By CONTACT_NAME_FIELD(int rowNo) {
        return By.xpath("//div[@aria-label='Data table']//tr[" + rowNo + "]//td[2]");
    }

    static By JOB_TITLE_FIELD(int rowNo) {
        return By.xpath("//div[@aria-label='Data table']//tr[" + rowNo + "]//td[3]");
    }

    static By PHONE_NUMBER_FIELD(int rowNo) {
        return By.xpath("//div[@aria-label='Data table']//tr[" + rowNo + "]//td[9]");
    }

    static By ADDRESS_FIELD(int rowNo) {
        return By.xpath("//div[@aria-label='Data table']//tr[" + rowNo + "]//td[10]");
    }


    // Locate all rows in the employee table
    public EmployeesPage clickCountryMenu() {
        click(COUNTRY_MENU);
        return this;
    }

    public EmployeesPage clickFilterDropdown() {
        waitForElementPresent(FILTER_DROPDOWN, 4L);
        click(FILTER_DROPDOWN);
        return this;
    }

    public EmployeesPage typeInFilterField(String country) {
        //waitForElementPresent(COUNTRY_MENU_TEXT_FIELD, 4L);
        click(COUNTRY_MENU_TEXT_FIELD);
        type(COUNTRY_MENU_TEXT_FIELD, country);
        return this;
    }

    public EmployeesPage clickFilterButton() {
        waitForElementPresent(FILTER_BUTTON, 4L);
        click(FILTER_BUTTON);
        return this;
    }

    public EmployeesPage clickStatusColumn() {
        waitForElementPresent(STATUS_COLUMN_MENU, 4L);
        click(STATUS_COLUMN_MENU);
        return this;
    }

    public EmployeesPage clickExportToExcelButton() {
        click(EXPORT_TO_EXCEL_BUTTON);
        return this;
    }

    public EmployeesPage selectRadioButton(By by) {
        WebElement radioButton = findElement(by);
        // Check if the radio button is not already selected
        if (!radioButton.isSelected()) {
            radioButton.click();
            System.out.println("Radio button selected.");
        } else {
            System.out.println("Radio button is already selected.");

       }
        return this;
}

    public EmployeesPage employeesData() {
        List<WebElement> rows = new ArrayList<>(findElements(DATA_TABLE_LIST));
        for (int i = 1; i < rows.size() - 1; i++) {
            System.out.println("Contact Name - " + findElement(CONTACT_NAME_FIELD(i)).getText());
            System.out.println("Job title - " + findElement(JOB_TITLE_FIELD(i)).getText());
            System.out.println("Phone number - " + findElement(PHONE_NUMBER_FIELD(i)).getText());
            System.out.println("Address - " + findElement(ADDRESS_FIELD(i)).getText());
            System.out.println("====================================");
        }
        return this;
    }
}




