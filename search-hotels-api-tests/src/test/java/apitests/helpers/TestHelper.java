package apitests.helpers;

import apitests.model.City;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;

import java.util.List;

public class TestHelper {

    protected String getCityId(String cityName, String country) {
        String json = RestAssured.get("https://www.onetwotrip.com/_hotels/api/suggestRequest?query="
                + cityName.toLowerCase() + "&limit=7&locale=ru&currency=rub&lang=ru").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement cities = parsed.getAsJsonObject().get("result");
        List<City> listOfCities = new Gson().fromJson(cities, new TypeToken<List<City>>(){}.getType());
        for (City c : listOfCities) {
            if (c.getName().equals(cityName) && c.getCountry().equals(country)) {
                return c.getId();
            }
        }
        return null;
    }

    protected String getRequestId(String cityId, String adultsNumber, String childrenNumber) {
        String json =  RestAssured.get("https://www.onetwotrip.com/_hotels/api/searchRequest?" +
                "object_id=" + cityId + "&object_type=geo&lang=ru&locale=ru&currency=RUB&date_start=2017-09-25&" +
                "date_end=2017-09-30&pos=1&adults=" + adultsNumber + "&children=" + childrenNumber + "").asString();
        JsonElement parsed = new JsonParser().parse(json);
        String requestId = String.valueOf(parsed.getAsJsonObject().get("result").getAsJsonObject().get("request_id"));
        return requestId.substring(1, requestId.length() - 1);
    }


}

