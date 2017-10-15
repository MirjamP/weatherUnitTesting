package weather;

/**
 * Created by mirja on 15/10/2017.
 */
public class CoordinatesOfCity {


    public double latitude;
    public double longitude;

    public CoordinatesOfCity() {
    }

    @Override
    public String toString(){
        return longitude + ":" + latitude;
    }


}
