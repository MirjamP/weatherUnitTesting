package open.data;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class OpenWeatherForecastDataPoint {
    @SerializedName("dt") long timestamp;
    @SerializedName("main") OpenWeatherMain temperature;
}
