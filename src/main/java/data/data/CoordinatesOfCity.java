package data.data;

import lombok.NonNull;
import lombok.Value;

@Value
public class CoordinatesOfCity {
    @NonNull double latitude;
    @NonNull double longitude;

    @Override
    public String toString(){
        return longitude + ":" + latitude;
    }

}
