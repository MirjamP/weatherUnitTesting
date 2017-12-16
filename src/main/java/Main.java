import data.service.APIWeatherRequest;
import open.HttpReader;
import open.JsonConverter;
import open.OpenWeatherConverter;
import open.OpenWeatherRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by mirja on 16/12/2017.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        WeatherApp app = new WeatherApp(
                new OpenWeatherRepository(
                        new HttpReader(),
                        new OpenWeatherConverter(),
                        new JsonConverter()
                )
        );

        Scanner fileScanner = new Scanner(new File("input.txt"));

        // System in → System out
        Supplier<APIWeatherRequest> sysIn = () -> {
            try {
                return new APIWeatherRequest(promptForString("Enter city"), promptForString("Enter country code"));
            } catch (NoSuchElementException e) {
                return null; // Out of input
            }
        };
        Function<String, PrintWriter> sysOut = (cityName) -> new PrintWriter(System.out);
        Supplier<APIWeatherRequest> fileIn = () -> {
            try {
                String[] split = fileScanner.nextLine().split(";");
                return new APIWeatherRequest(split[0], split[1]);
            } catch (NoSuchElementException e) {
                return null; // Out of input
            }
        };
        Function<String, PrintWriter> fileOut = (cityName) -> {
            try {
                return new PrintWriter(new File("output/" + cityName + ".txt"));
            } catch (FileNotFoundException e) {
                throw new Error(e);
            }
        };


        app.analyze(sysIn, fileOut);
    }

    private static String promptForString(final String prompt) {
        Scanner in = new Scanner(System.in);
        String city;
        System.out.print(prompt + ": ");
        System.out.flush();
        city = in.nextLine();
        return city;
    }
}
