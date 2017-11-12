package open;

import java.util.ArrayList;
import java.util.Date;

import data.data.City;
import data.data.CoordinatesOfCity;
import data.data.OneDay;
import data.data.Temperature;
import data.service.APIWeatherForecast;
import data.service.APIWeatherReport;
import open.data.OpenWeatherForecast;
import open.data.OpenWeatherReport;


public class OpenWeatherConverter {
    APIWeatherForecast convert(OpenWeatherForecast forecast){
        ArrayList<OneDay> dayReports = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            double max = Double.MIN_VALUE;
            double min = Double.MAX_VALUE;
            double current = 0;
            long mostRecentTime = 0;
            for (int j = i; j < i + 8; j++) {
                min = Math.min(forecast.getDataPoints().get(j).getTemperature().getMin(), min);
                max = Math.min(forecast.getDataPoints().get(j).getTemperature().getMax(), max);
                if(mostRecentTime < forecast.getDataPoints().get(j).getTimestamp()){
                    mostRecentTime = forecast.getDataPoints().get(j).getTimestamp();
                    current = forecast.getDataPoints().get(j).getTemperature().getTemp();
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
                new City(forecast.getCity().getName(), forecast.getCity().getCountry()),
                new CoordinatesOfCity(forecast.getCity().getCoord().getLat(), forecast.getCity().getCoord().getLon()),
                dayReports
        );
    }

    public APIWeatherReport convert(OpenWeatherReport report) {
        return new APIWeatherReport(
                new City(report.getCity(), report.getCountry()),
                new CoordinatesOfCity(report.getCoordinates().getLat(), report.getCoordinates().getLon()),
                new Temperature(report.getMain().getMax(), report.getMain().getMin(), report.getMain().getTemp())
        );
    }
}
