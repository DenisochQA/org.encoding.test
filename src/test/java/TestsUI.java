import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestsUI {

    private WebDriver driver;
    private GetStartedPage page;
    private String searchResultURL = "https://api.encoding.com/reference#responses-getstatus-extended";
    private String processorParams = "[AMAZON | RACKSPACE]";
    private String formatStatusValue = "Status";


    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Denis\\Desktop\\Automation\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://api.encoding.com/");
    }

    @Test
    public void executeSearch() {
        page = new GetStartedPage(driver);
        page.getSearchInput("getStatus");
        page.chooseSearchValue("GetStatus (extended)");

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.urlMatches(searchResultURL));

        Assert.assertEquals(driver.getCurrentUrl(), searchResultURL);
        page.chooseRequestType("JSON");
        Assert.assertEquals(page.getProcParamValue(), processorParams);
        Assert.assertEquals(page.getFormatParamStatusValue(), formatStatusValue);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
