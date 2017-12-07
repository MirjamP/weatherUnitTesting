package data.service;

import java.io.IOException;
import java.net.MalformedURLException;

import lombok.NonNull;


public interface IWeatherRepository {
    @NonNull APIWeatherReport getCurrentWeather(APIWeatherRequest request) throws IOException;
    @NonNull APIWeatherForecast getForecastThreeDays(APIWeatherRequest request) throws IOException;

}
