package weather;

public class APIWeatherReport {

    private String cityName;
    private String coordinates;
    public int tempCurrent;


    public APIWeatherReport(String linn, String coordinates) {
        this.cityName = linn;
        this.coordinates = coordinates;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public int getCurrentTemp() {
        return tempCurrent;
    }
}
