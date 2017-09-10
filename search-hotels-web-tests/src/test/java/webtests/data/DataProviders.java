package webtests.data;

import org.testng.annotations.DataProvider;
import webtests.model.SearchRequest;


public class DataProviders {

    @DataProvider(name = "location")
    public static Object[][] location() {
        String cityName = "Москва";
        return new Object[][]{new Object[] {new SearchRequest().withCity(cityName)} };
    }
}
