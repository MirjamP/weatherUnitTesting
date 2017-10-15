package Exceptions;

public class NoWeatherFoundException extends Exception {
    public NoWeatherFoundException(String errMsg) {
        super("Error: " + errMsg);
    }
}