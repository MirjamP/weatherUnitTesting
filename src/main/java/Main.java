import data.service.APIWeatherForecast;
import data.service.APIWeatherReport;
import data.service.APIWeatherRequest;
import open.OpenWeatherRepository;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by mirja on 12/11/2017.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        OpenWeatherRepository repo = new OpenWeatherRepository();
        Scanner in = new Scanner(System.in);


        while(true){
            String city;
            String country;
            try{
                System.out.print("Enter city: ");
                city = in.nextLine();
                System.out.print("Enter country code: ");
                country = in.nextLine();
            } catch(NoSuchElementException e){
                break;
            }
            APIWeatherReport current = repo.getCurrentWeather(new APIWeatherRequest(city, country));
            APIWeatherForecast forecast = repo.getForecastThreeDays(new APIWeatherRequest(city, country));

            forecast.getDayReports().stream().forEach(f -> {
                System.out.println(f.getDate());
                System.out.println(f.getTemperature().getMax());
            });
            System.out.println("==== Report for " + city + "====");
            System.out.print("Current temperature:");
            System.out.println(current.getTemperature());
            System.out.print("3 day min: ");
            System.out.println(forecast.getDayReports().stream().mapToDouble(f -> f.getTemperature().getMin()).min()
                    .getAsDouble());
            System.out.print("3 day max: ");
            System.out.println(forecast.getDayReports().stream().mapToDouble(f -> f.getTemperature().getMax())
                    .max().getAsDouble());
            System.out.print("Coordinates: ");
            System.out.println(current.getCity().getCoordinates().getLatitude() + ", " + current.getCity().getCoordinates
                    ().getLongitude());
        };
    }
}
