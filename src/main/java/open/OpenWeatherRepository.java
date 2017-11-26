package open;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.service.APIWeatherForecast;
import data.service.APIWeatherReport;
import data.service.IWeatherRepository;
import data.service.APIWeatherRequest;
import open.data.OpenWeatherForecast;
import open.data.OpenWeatherReport;

public class OpenWeatherRepository implements IWeatherRepository {
    private static final String APP_ID = ResourceBundle.getBundle("strings").getString("APP_ID");
    private final String FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast";
    private final String REPORT_URL = "http://api.openweathermap.org/data/2.5/weather";

    private final OpenWeatherConverter openWeatherConverter;
    private final HttpReader httpReader;
    private final JsonConverter jsonConverter;

    public OpenWeatherRepository(HttpReader reader, OpenWeatherConverter converter, JsonConverter jsonConverter) {
        this.openWeatherConverter = converter;
        this.httpReader = reader;
        this.jsonConverter = jsonConverter;
    }

    @Override
    public APIWeatherForecast getForecastThreeDays(APIWeatherRequest request) throws IOException {
        String url = FORECAST_URL+"?q="+request.getCityName()+","+request.getCountry()+"&units=metric&&APPID="+APP_ID;
        OpenWeatherForecast forecast = jsonConverter.parseForecast(httpReader.read(new URL(url)));
        return openWeatherConverter.convert(forecast);
    }
    @Override
    public APIWeatherReport getCurrentWeather(APIWeatherRequest request) throws IOException {
        String url = REPORT_URL+"?q="+request.getCityName()+","+request.getCountry()+"&units=metric&APPID="+APP_ID;
        OpenWeatherReport report = jsonConverter.parseReport(httpReader.read(new URL(url)));
        return openWeatherConverter.convert(report);
    }
}
