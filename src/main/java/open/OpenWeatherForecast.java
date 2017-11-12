package open;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import data.data.Temperature;
import lombok.Value;

public class OpenWeatherForecast {

    @SerializedName("list") List<ForecastDataPoint> dataPoints;
    @SerializedName("city") OpenWeatherCity city;

    class ForecastDataPoint {
        @SerializedName("dt") long timestamp;
        @SerializedName("main") OpenWeatherTemperature temperature;

        class OpenWeatherTemperature {
            @SerializedName("temp") double temp;
            @SerializedName("temp_min") double min;
            @SerializedName("temp_max") double max;
        }
    }

    class OpenWeatherCity {
        @SerializedName("name") String name;
        @SerializedName("country") String country;
        @SerializedName("coord") OpenWeatherCoordinates coord;

        class OpenWeatherCoordinates {
            @SerializedName("lat") double lat;
            @SerializedName("lon") double lon;
        }
    }
}
