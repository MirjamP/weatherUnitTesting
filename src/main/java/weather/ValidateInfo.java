package weather;

/**
 * Created by mirja on 28/09/2017.
 */
public class ValidateInfo {


    private boolean tempRight = false;
    private boolean cordRight = false;

    public ValidateInfo(boolean tempRight, boolean cordRight) {
        this.tempRight = tempRight;
        this.cordRight = cordRight;
    }

    //TODO: kontrolli kas temp on õiges formaadis
    public boolean validateTemperature() {
        return tempRight;
    }

    //TODO: kontrolli kas koordinaadid on õiges formaadis vastavalt sellele, mida soovid
    public boolean validateCoordinates() {
        return cordRight;
    }
}
