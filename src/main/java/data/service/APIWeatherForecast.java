package data.service;

import java.util.Date;
import java.util.List;

import data.data.City;
import data.data.CoordinatesOfCity;
import data.data.OneDay;
import lombok.NonNull;
import lombok.Value;


@Value
public class APIWeatherForecast {
    @NonNull City city;
    @NonNull List<OneDay> dayReports;
}
