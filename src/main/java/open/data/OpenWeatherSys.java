package open.data;


import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class OpenWeatherSys {
    @SerializedName("country") String country;
}
