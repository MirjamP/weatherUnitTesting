package open;

import com.google.gson.Gson;
import open.data.OpenWeatherForecast;
import open.data.OpenWeatherReport;

import java.io.InputStreamReader;

/**
 * Created by mirja on 26/11/2017.
 */
public class JsonConverter {
    public OpenWeatherForecast parseForecast(InputStreamReader reader){
        return new Gson().fromJson(reader, OpenWeatherForecast.class);
    }
    public OpenWeatherReport parseReport(InputStreamReader reader){
        return new Gson().fromJson(reader, OpenWeatherReport.class);
    }
}
