package open.data;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class OpenWeatherReport {
    @SerializedName("main") OpenWeatherMain main;
    @SerializedName("coord") OpenWeatherCoordinates coordinates;
    @SerializedName("dt") long dt;
    @SerializedName("sys") OpenWeatherSys sys;
    @SerializedName("name") String city;

    public String getCountry(){
        return sys.getCountry();
    }
}
