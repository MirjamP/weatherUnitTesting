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

        for (int i = 0; i < 5; i++) {
            double max = Double.MIN_VALUE;
            double min = Double.MAX_VALUE;
            for (int j = i*8; j < i*8 + 8; j++) {
                min = Math.min(forecast.getDataPoints().get(j).getTemperature().getMin(), min);
                max = Math.max(forecast.getDataPoints().get(j).getTemperature().getMax(), max);
            }
            dayReports.add(
                    new OneDay(
                            new Temperature(max, min),
                            new Date(forecast.getDataPoints().get(i*8).getTimestamp()*1000)
                    )
            );
        }

        return new APIWeatherForecast(
                new City(forecast.getCity().getName(), forecast.getCity().getCountry(), new CoordinatesOfCity(forecast.getCity().getCoord().getLat(), forecast.getCity().getCoord().getLon())), dayReports
        );
    }

    public APIWeatherReport convert(OpenWeatherReport report) {
        return new APIWeatherReport(
                new City(report.getCity(), report.getCountry(), new CoordinatesOfCity(report.getCoordinates().getLat(), report.getCoordinates().getLon())),
                new Temperature(report.getMain().getMax(), report.getMain().getMin()),
                        report.getMain().getTemp()
        );
    }
}
