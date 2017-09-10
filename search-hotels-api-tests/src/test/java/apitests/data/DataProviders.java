package apitests.data;


import org.testng.annotations.DataProvider;

public class DataProviders {


    @DataProvider(name = "location")
    public static Object[][] location() {
        String cityName = "Москва";
        String country = "Россия";
        String childrenNumber = "0";
        String adultsNumber = "1";
        return new Object[][]{new Object[] {cityName, country, childrenNumber, adultsNumber} };
    }
}
