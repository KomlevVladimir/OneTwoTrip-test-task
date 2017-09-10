package webtests.appmanager;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import webtests.model.SearchRequest;
import webtests.pages.SearchHotelsPage;
import webtests.pages.SearchResultPage;


public class ApplicationManager {

    private String browser;
    private WebDriver wd;

    private SearchHotelsPage searchHotelsPage;
    private SearchResultPage searchResultPage;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        switch (browser) {
            case BrowserType.FIREFOX:
                wd = new FirefoxDriver();
                break;
            case BrowserType.CHROME:
                wd = new ChromeDriver();
                break;
            case BrowserType.IE:
                wd = new InternetExplorerDriver();
                break;
        }

        searchHotelsPage = new SearchHotelsPage(wd);
        searchResultPage = new SearchResultPage(wd);
    }

    public void stop() {
        wd.quit();
    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void searchHotels(SearchRequest request) throws InterruptedException {
        searchHotelsPage.searchHotelsPageOpen();
        searchHotelsPage.selectCity(request.getCity());
        searchHotelsPage.buttonSubmit.click();
        searchResultPage.waitUntilLoad();
    }

    public String getCityFromResultPage() {
        String title = searchResultPage.titleSearchResult.getText();
        return title.split(",")[0];
    }

    public int getOffers() {
        String number = searchResultPage.hotelsNumber.getText();
        return Integer.valueOf(number.split(" ")[1]);
    }
}
