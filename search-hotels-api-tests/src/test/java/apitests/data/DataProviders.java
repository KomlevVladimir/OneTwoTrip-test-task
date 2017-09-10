package apitests.data;


import org.testng.annotations.DataProvider;

public class DataProviders {


    @DataProvider(name = "location")
    public static Object[][] location() {
        String cityName = "Москва";
        String country = "Россия";
        String childrenNumber = "0";
        String adultsNumber = "1";
        String startDate = "2017-09-25";
        String endDate = "2017-09-30";

        return new Object[][]{new Object[] {cityName, country, childrenNumber, adultsNumber, startDate, endDate} };
    }
}
