package open;

import data.service.APIWeatherForecast;
import data.service.APIWeatherReport;
import data.service.IWeatherRepository;
import data.service.APIWeatherRequest;

/**
 * Created by mirja on 15/10/2017.
 */
public class OpenWeatherRepository implements IWeatherRepository {
    @Override
    public APIWeatherForecast getForecastThreeDays(APIWeatherRequest request){
        return null; //TODO:
    }
    @Override
    public APIWeatherReport getCurrentWeather(APIWeatherRequest request){
        return null; //TODO:
    }
}
