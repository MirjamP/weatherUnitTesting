package open;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import data.service.APIWeatherForecast;
import data.service.APIWeatherReport;
import data.service.IWeatherRepository;
import data.service.APIWeatherRequest;

/**
 * Created by mirja on 15/10/2017.
 */
public class OpenWeatherRepository implements IWeatherRepository {
    private static final String APP_ID = "";
    private final String FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast";

    @Override
    public APIWeatherForecast getForecastThreeDays(APIWeatherRequest request) throws IOException {
        String url = FORECAST_URL+"?q="+request.getCityName()+","+request.getCountry()+"&APPID="+APP_ID;
        OpenWeatherForecast forecast = new Gson().fromJson(new InputStreamReader(new URL(url).openStream()), OpenWeatherForecast.class);
        return new ForecastConverter().convert(forecast);
    }
    @Override
    public APIWeatherReport getCurrentWeather(APIWeatherRequest request) throws IOException {
        return null; //TODO:
    }
}
