import data.data.City;
import data.data.CoordinatesOfCity;
import data.data.Temperature;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
class ValidateInfo {

    private final Temperature temperature;
    private final CoordinatesOfCity coordinates;


    boolean validateTemperature() {
        double max = temperature.getMax();
        double min = temperature.getMin();

        boolean maxValid = -80 <= max && max <= 80;
        boolean minValid = -80 <= min && min <= 80;
        boolean areInOrder = min <= max;

        return maxValid && minValid && areInOrder;
    }


    boolean validateCoordinates() {
        if(Math.abs(coordinates.getLatitude()) < 90) {
            if (Math.abs(coordinates.getLongitude()) < 180) {
                return true;
            }
        }
        return false;
    }
}
