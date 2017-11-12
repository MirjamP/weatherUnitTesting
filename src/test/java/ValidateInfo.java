import data.data.City;
import data.data.CoordinatesOfCity;
import data.data.Temperature;
import lombok.RequiredArgsConstructor;

/**
 * Created by mirja on 28/09/2017.
 */
@RequiredArgsConstructor
public class ValidateInfo {

    private final Temperature temperature;
    private final CoordinatesOfCity coordinates;


    public boolean validateTemperature() {
        double current = temperature.getCurrent();
        double max = temperature.getMax();
        double min = temperature.getMin();

        boolean currentValid = 200 <= current && current <= 350;
        boolean maxValid = 200 <= max && max <= 350;
        boolean minValid = 200 <= min && min <= 350;
        boolean areInOrder = min <= current && current <= max;

        return currentValid && maxValid && minValid && areInOrder;
    }


    public boolean validateCoordinates() {
        if(Math.abs(coordinates.getLatitude()) < 90) {
            if (Math.abs(coordinates.getLongitude()) < 180) {
                return true;
            }
        }
        return false;
    }
}
