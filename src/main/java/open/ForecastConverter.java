package open;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import data.data.City;
import data.data.CoordinatesOfCity;
import data.data.OneDay;
import data.data.Temperature;
import data.service.APIWeatherForecast;


public class ForecastConverter {
    APIWeatherForecast convert(OpenWeatherForecast forecast){
        ArrayList<OneDay> dayReports = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            double max = Double.MIN_VALUE;
            double min = Double.MAX_VALUE;
            double current = 0;
            long mostRecentTime = 0;
            for (int j = i; j < i + 8; j++) {
                min = Math.min(forecast.dataPoints.get(j).temperature.min, min);
                max = Math.min(forecast.dataPoints.get(j).temperature.max, max);
                if(mostRecentTime < forecast.dataPoints.get(j).timestamp){
                    mostRecentTime = forecast.dataPoints.get(j).timestamp;
                    current = forecast.dataPoints.get(j).temperature.temp;
                }
            }
            dayReports.add(
                    new OneDay(
                            new Temperature(max, min, current),
                            new Date(mostRecentTime)
                    )
            );
        }

        return new APIWeatherForecast(
                new City(forecast.city.name, forecast.city.country),
                new CoordinatesOfCity(forecast.city.coord.lat, forecast.city.coord.lon),
                dayReports
        );
    }
}
