package open.data;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class OpenWeatherMain {
    @SerializedName("temp") double temp;
    @SerializedName("temp_min") double min;
    @SerializedName("temp_max") double max;
}
