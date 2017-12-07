package open;

import com.google.gson.Gson;
import open.data.OpenWeatherForecast;
import open.data.OpenWeatherReport;

import java.io.InputStreamReader;


public class JsonConverter {
    public OpenWeatherForecast parseForecast(InputStreamReader reader){
        return new Gson().fromJson(reader, OpenWeatherForecast.class);
    }
    OpenWeatherReport parseReport(InputStreamReader reader){
        return new Gson().fromJson(reader, OpenWeatherReport.class);
    }
}
