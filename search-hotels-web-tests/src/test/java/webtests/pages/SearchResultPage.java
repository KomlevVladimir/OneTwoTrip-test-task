package webtests.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchResultPage extends BasePage {

    public SearchResultPage(WebDriver wd) {
        super(wd);
        PageFactory.initElements(wd, this);
    }

    @FindBy(xpath = "//span[@class='bRHit']")
    public WebElement titleSearchResult;

    @FindBy(xpath = "//div[@class='jWJsU']")
    public WebElement hotelsNumber;

    public void waitUntilLoad() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='_5tmsc']")));
    }
}
