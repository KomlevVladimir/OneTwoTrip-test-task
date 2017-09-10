package webtests.tests;

import org.testng.annotations.Test;

import webtests.data.DataProviders;
import webtests.model.SearchRequest;

import static org.testng.Assert.*;

public class SearchHotelsWebTests extends TestBase {

    @Test(dataProvider = "location", dataProviderClass = DataProviders.class)
    public void testSearchHotels(SearchRequest searchRequest) throws InterruptedException {
       app.searchHotels(searchRequest);

        assertEquals(app.getCityFromResultPage(), searchRequest.getCity());
        assertTrue(app.getOffers() > 0);
    }

}
