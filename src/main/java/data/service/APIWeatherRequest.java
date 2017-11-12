package data.service;

import lombok.NonNull;
import lombok.Value;

@Value
public class APIWeatherRequest {
    @NonNull String cityName;
    @NonNull String country;
}
