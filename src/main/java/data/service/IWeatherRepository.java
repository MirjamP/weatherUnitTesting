package data.service;

import lombok.NonNull;

/**
 * Created by mirja on 28/09/2017.
 */
public interface IWeatherRepository {
    //TODO: loe interface kohta või vaata proge Card interface
    @NonNull APIWeatherReport getCurrentWeather(APIWeatherRequest request);
    @NonNull APIWeatherForecast getForecastThreeDays(APIWeatherRequest request);

}
