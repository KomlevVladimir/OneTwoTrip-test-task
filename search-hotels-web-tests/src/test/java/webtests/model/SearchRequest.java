package webtests.model;


public class SearchRequest {

    private String city;

    @Override
    public String toString() {
        return "SearchRequest{" +
                "city='" + city + '\'' +
                '}';
    }

    public String getCity() {
        return city;
    }

    public SearchRequest withCity(String city) {
        this.city = city;
        return this;
    }
}
