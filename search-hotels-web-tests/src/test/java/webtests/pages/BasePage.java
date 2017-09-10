package webtests.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver wd;
    protected WebDriverWait wait;

    public BasePage (WebDriver wd) {
        this.wd = wd;
        wait = new WebDriverWait(wd, 10);
    }

    protected void click(By locator)
    {
        wd.findElement(locator).click();
    }

    protected void fillForm(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

}
