package open.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class OpenWeatherForecast {

    @SerializedName("list") List<OpenWeatherForecastDataPoint> dataPoints;
    @SerializedName("city") OpenWeatherCity city;

}
