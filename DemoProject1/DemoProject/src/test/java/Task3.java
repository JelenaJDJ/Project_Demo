import base.BasePage;
import base.BaseTest;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.EmployeesPage;

public class Task3 extends BaseTest {

    @Description("This test verify that url is loaded, verify all employees from USA, for each employee printed Name, Job title, phone number and address")
    @Test
    public void employeesData(){
        new BasePage(driver).loadUrl("task3_URL");
        EmployeesPage employeesPage = new EmployeesPage(driver);
        employeesPage.clickCountryMenu()
                .clickFilterDropdown()
                .typeInFilterField("US")
                .clickFilterButton()
                .employeesData()
                .clickStatusColumn()
                .clickFilterDropdown()
                .selectRadioButton(By.xpath("(//kendo-grid-boolean-filter-menu//kendo-radiobutton)[1]"))
                .clickFilterButton()
                .clickExportToExcelButton();

    }
}
