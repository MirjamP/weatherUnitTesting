package data.service;

import data.data.City;
import data.data.CoordinatesOfCity;
import data.data.Temperature;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;


@Value
public class APIWeatherReport {

    @NonNull City city;
    @NonNull CoordinatesOfCity coordinates;
    @NonNull Temperature temperature;
}
