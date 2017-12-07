import com.google.gson.Gson;
import data.service.APIWeatherForecast;
import data.service.APIWeatherReport;
import data.service.APIWeatherRequest;
import open.HttpReader;
import open.JsonConverter;
import open.OpenWeatherConverter;
import open.OpenWeatherRepository;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        OpenWeatherRepository repo = new OpenWeatherRepository(new HttpReader(), new OpenWeatherConverter(), new JsonConverter());

        dealWithFiles(repo);
        dealWithConsole(repo);
    }

    private static void dealWithConsole(OpenWeatherRepository repo) throws IOException {
        Scanner in = new Scanner(System.in);
        while(true){
            String city;
            String country;
            try{
                System.out.print("Enter city: ");
                city = in.nextLine();
                System.out.print("Enter country code: ");
                country = in.nextLine();
            } catch(Exception e){
                break;
            }
            try {
                APIWeatherReport current = repo.getCurrentWeather(new APIWeatherRequest(city, country));
                APIWeatherForecast forecast = repo.getForecastThreeDays(new APIWeatherRequest(city, country));
                forecast.getDayReports().stream().forEach(f -> {
                    System.out.println(f.getDate());
                    System.out.println(f.getTemperature().getMax());
                    System.out.println(f.getTemperature().getMin());
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
            } catch(Exception p) {
                break;
            }
        }
    }

    private static void dealWithFiles(OpenWeatherRepository repo) throws IOException {
        File inputFile = new File("input.txt");
        try {
            Scanner sc = new Scanner(inputFile);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String[] split = s.split(";");
                APIWeatherReport current = repo.getCurrentWeather(new APIWeatherRequest(split[0], split[1]));
                APIWeatherForecast forecast = repo.getForecastThreeDays(new APIWeatherRequest(split[0], split[1]));
                String cityName = current.getCity().getName();
                String longitude = String.valueOf(current.getCity().getCoordinates().getLongitude());
                String latitude = String.valueOf(current.getCity().getCoordinates().getLatitude());
                String temperature = String.valueOf(current.getTemperature());

                double max1 = forecast.getDayReports().get(0).getTemperature().getMax();
                double min1 = forecast.getDayReports().get(0).getTemperature().getMin();

                double max2 = forecast.getDayReports().get(1).getTemperature().getMax();
                double min2 = forecast.getDayReports().get(1).getTemperature().getMin();

                double max3 = forecast.getDayReports().get(2).getTemperature().getMax();
                double min3 = forecast.getDayReports().get(2).getTemperature().getMin();

                PrintWriter writer = new PrintWriter( cityName + ".txt", "UTF-8");
                writer.println(cityName + "\n" + longitude + " " + latitude + "\n" + temperature + "\n");
                writer.println("Forecast max and min for the next three days: \n" + max1 + "\n" + min1 + "\n" + max2
                        + "\n" + min2 + "\n" + max3 + "\n" + min3);
                writer.close();

                System.out.println(current);
                System.out.println(forecast);
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
