package open.data;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class OpenWeatherCoordinates {
    @SerializedName("lat") double lat;
    @SerializedName("lon") double lon;
}
