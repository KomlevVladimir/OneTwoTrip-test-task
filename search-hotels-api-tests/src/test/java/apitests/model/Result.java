package apitests.model;


import java.util.List;

public class Result {

    private List offers;
    private String status;

    @Override
    public String toString() {
        return "Result{" +
                "offers=" + offers +
                ", status='" + status + '\'' +
                '}';
    }

    public List getOffers() {
        return offers;
    }

    public String getStatus() {
        return status;
    }
}
