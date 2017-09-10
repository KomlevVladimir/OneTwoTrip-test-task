package webtests.tests;

import org.testng.annotations.Test;

import webtests.data.DataProviders;
import webtests.model.SearchRequest;

import static org.testng.Assert.*;

public class SearchHotelsWebTests extends TestBase {

    @Test(dataProvider = "location", dataProviderClass = DataProviders.class)
    public void testSearchHotels(SearchRequest request) throws InterruptedException {
       app.searchHotels(request);

        assertEquals(request.getCity(), app.getCityFromResultPage());
        assertTrue(app.getNumberOfHotels() > 0);


    }


    

}
