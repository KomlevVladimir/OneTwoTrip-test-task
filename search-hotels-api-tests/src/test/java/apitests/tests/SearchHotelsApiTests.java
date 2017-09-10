package apitests.tests;

import apitests.data.DataProviders;
import apitests.helpers.TestHelper;
import apitests.model.Result;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchHotelsApiTests extends TestHelper {

    @Test(dataProvider = "location", dataProviderClass = DataProviders.class)
    public void testSearchHotels(String cityName, String country, String childrenNumber,
                                 String adultsNumber, String startDate, String endDate) {
        String cityId = getCityId(cityName, country);
        String requestId = getRequestId(cityId, adultsNumber, childrenNumber, startDate, endDate);

        String json = RestAssured.get("https://www.onetwotrip.com/_hotels/api/searchPolling?request_id="
                + requestId + "&lang=ru&locale=ru&currency=RUB").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement response = parsed.getAsJsonObject().get("result");
        Result result = new Gson().fromJson(response, Result.class);

        String rr = result.getStatus();
        assertTrue(result.getOffers().size() > 0);
        assertTrue(result != null);
        assertEquals(result.getStatus(), "done");

    }

}
