import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

public class GetStartedPage {

    private WebDriver driver;

    public GetStartedPage(WebDriver driver) {
        this.driver = driver;
    }

    private By mainSearchBox = By.xpath("//button[@class='searchbox searchbox-button searchbox-input']");
    private By searchBoxInput = By.xpath("//input[@class='Input Input_md _14cwP-SearchBox-Input']");
    private String searchResultsList = "//div[@class='SearchResults-list']/a/header/span[text()='%s']";
    private String responseTypeButton = "//div[@id='page-responses-getstatus-extended']//div[@class='hub-reference-left']//div[2]//div[1]//button[text()='%s']";
//    private By procParamValue = By.xpath("//span[@class='cm-property' and contains(text(),'processor')]/following-sibling::span[1]");



    public GetStartedPage getSearchInput(String value) {
        driver.findElement(mainSearchBox).click();
        driver.findElement(searchBoxInput).sendKeys(value);
        return this;
    }

    public GetStartedPage chooseSearchValue(String value) {
        driver.findElement(xpath(format(searchResultsList, value))).click();
        return this;
    }


    public GetStartedPage chooseRequestType(String name) {
        driver.findElement(xpath(format(responseTypeButton, name))).click();
        return this;
    }

    public String getProcParamValue() {
        String myString = driver.findElement(By.xpath("//span[@class='cm-property' and contains(text(),'processor')]/following-sibling::span[1]")).getText();
        myString = myString.substring(1, myString.length()-1);
        return myString;
    }

    public String getFormatParamStatusValue() {
        String myString = driver.findElement(By.xpath("//pre[@class='CodeTabs_active']//span[@class='cm-property' and contains(text(),'format')]" +
                                                      "/following-sibling::span[@class='cm-string' and contains(text(),'[Status]')]")).getText();
        myString = myString.substring(2, myString.length()-2);
        return myString;
    }


}
