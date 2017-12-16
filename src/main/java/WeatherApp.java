import data.service.APIWeatherForecast;
import data.service.APIWeatherReport;
import data.service.APIWeatherRequest;
import javafx.util.Pair;
import open.HttpReader;
import open.JsonConverter;
import open.OpenWeatherConverter;
import open.OpenWeatherRepository;

import java.io.*;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;


public class WeatherApp {

    private OpenWeatherRepository repo;

    public WeatherApp(OpenWeatherRepository repo) {
        this.repo = repo;
    }

    /**
     * @param input  A supplier of pairs of city names and country codes - should return null when out of input
     * @param output A supplier of outputWriters - is given a city name to generate a writer for
     * @throws IOException sometimes
     */
    public void analyze(Supplier<APIWeatherRequest> input,Function<String, PrintWriter> output) throws IOException {

        while (true) {
            APIWeatherRequest request = input.get();
            if(request == null){
                return;
            }

            APIWeatherReport current = repo.getCurrentWeather(request);
            APIWeatherForecast forecast = repo.getForecastThreeDays(request);

            String cityName = current.getCity().getName();

            PrintWriter writer = output.apply(cityName);
            writeReport(current, forecast, writer);
            // Note: Do not close the PrintWriter, System.out needs to be kept open
        }
    }

    private void writeReport(APIWeatherReport current, APIWeatherForecast forecast, PrintWriter writer) {
        String cityName = current.getCity().getName();
        double longitude = current.getCity().getCoordinates().getLongitude();
        double latitude = current.getCity().getCoordinates().getLatitude();
        double temperature = current.getTemperature();
        Date day1 = forecast.getDayReports().get(0).getDate();
        double min1 = forecast.getDayReports().get(0).getTemperature().getMin();
        double max1 = forecast.getDayReports().get(0).getTemperature().getMax();
        Date day2 = forecast.getDayReports().get(1).getDate();
        double min2 = forecast.getDayReports().get(1).getTemperature().getMin();
        double max2 = forecast.getDayReports().get(1).getTemperature().getMax();
        Date day3 = forecast.getDayReports().get(2).getDate();
        double max3 = forecast.getDayReports().get(2).getTemperature().getMax();
        double min3 = forecast.getDayReports().get(2).getTemperature().getMin();

        writer.println("=== " + cityName + " ===");
        writer.println(" Loc: " + longitude + " lon, " + latitude + " lat");
        writer.println("Temp: " + temperature + "°C");
        writer.println();
        writer.println("Forecast max and min for the next three days: ");
        writer.println("  " + day1);
        writer.println("    " + min1 + "°C");
        writer.println("    " + max1 + "°C");
        writer.println("  " + day2);
        writer.println("    " + min2 + "°C");
        writer.println("    " + max2 + "°C");
        writer.println("  " + day3);
        writer.println("    " + min3 + "°C");
        writer.println("    " + max3 + "°C");
        writer.println();
        writer.flush();
    }

}
