package weather;

public class UserRequest {

    private String cityName;
    private String coordinates;

    public UserRequest(String linn, String coordinates) {
        this.cityName = linn;
        this.coordinates = coordinates;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCoordinates() {
        return coordinates;
    }
}
