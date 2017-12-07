package open;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import data.data.City;
import data.data.CoordinatesOfCity;
import data.data.OneDay;
import data.data.Temperature;
import data.service.APIWeatherForecast;
import data.service.APIWeatherReport;
import open.data.OpenWeatherForecast;
import open.data.OpenWeatherForecastDataPoint;
import open.data.OpenWeatherReport;


public class OpenWeatherConverter {
    public APIWeatherForecast convert(OpenWeatherForecast forecast){
        Calendar today = new GregorianCalendar();
        today.setTime(new Date());
        today.set(Calendar.HOUR_OF_DAY,0);
        today.set(Calendar.MINUTE,0);
        today.set(Calendar.SECOND,0);
        //final long startOfToday = today.getTimeInMillis();
        //final long lengthOfDay = 24*60*60*100;

        //ArrayList<OneDay> dayReports = new ArrayList<>();
        Function<Integer, Stream<OpenWeatherForecastDataPoint>> getDatapoints =
                (i) -> forecast.getDataPoints().parallelStream()
                        .filter(dp -> DateUtil.daysTo(dp.getTimestamp()*1000) == i);
        List<OneDay> dayReports = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            double max = getDatapoints.apply(i)
                    .mapToDouble(dp -> dp.getTemperature().getMax())
                    .max().getAsDouble();
            double min = getDatapoints.apply(i)
                    .mapToDouble(dp -> dp.getTemperature().getMin())
                    .min().getAsDouble();
            long timestamp = getDatapoints.apply(i)
                    .findAny().get()
                    .getTimestamp();

            dayReports.add(
                    new OneDay(
                            new Temperature(max, min),
                            DateUtil.getSameDay(timestamp*1000)
                    )
            );
        }


        return new APIWeatherForecast(
                new City(forecast.getCity().getName(), forecast.getCity().getCountry(),
                        new CoordinatesOfCity(forecast.getCity().getCoord().getLat(),
                                forecast.getCity().getCoord().getLon())), dayReports
        );
    }

    APIWeatherReport convert(OpenWeatherReport report) {
        return new APIWeatherReport(
                new City(report.getCity(), report.getCountry(), new CoordinatesOfCity(report.getCoordinates().getLat(),
                        report.getCoordinates().getLon())),
                new Temperature(report.getMain().getMax(), report.getMain().getMin()),
                        report.getMain().getTemp()
        );
    }
}
