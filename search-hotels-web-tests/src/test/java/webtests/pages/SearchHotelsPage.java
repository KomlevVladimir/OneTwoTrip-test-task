package webtests.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webtests.model.SearchRequest;

public class SearchHotelsPage extends BasePage{

    public SearchHotelsPage(WebDriver wd) {
        super(wd);
        PageFactory.initElements(wd, this);
    }

    public void searchHotelsPageOpen() {
        wd.get("https://www.onetwotrip.com/ru/hotels");
    }

    @FindBy(xpath = "//input[@name='to']")
    public WebElement inputTo;

    @FindBy(xpath = "//input[@name='dates']")
    public WebElement inputDates;

    @FindBy(xpath = "//input[@class='_-W4qI _2zQ-b _11G2N _-SCps']")
    public WebElement inputGuests;

    @FindBy(xpath = "//button[@class='_3cOT3 _2bd4P _3A_f- _2uP7O _2gw88']")
    public WebElement buttonSubmit;

    public void selectCity (String city) throws InterruptedException {
        fillForm(By.xpath("//input[@name='to']"), city);
        Thread.sleep(2000);
        wd.findElement(By.xpath("//input[@name='to']")).sendKeys(Keys.DOWN);
        wd.findElement(By.xpath("//input[@name='to']")).sendKeys(Keys.UP);
        wd.findElement(By.xpath("//input[@name='to']")).sendKeys(Keys.RETURN);

    }




}
