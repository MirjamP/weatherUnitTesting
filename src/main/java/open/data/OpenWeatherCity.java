package open.data;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class OpenWeatherCity {
    @SerializedName("name") String name;
    @SerializedName("country") String country;
    @SerializedName("coord") OpenWeatherCoordinates coord;

}
