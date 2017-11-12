package data.data;

import lombok.NonNull;
import lombok.Value;

@Value
public class City {
    @NonNull String name;
    @NonNull String countryCode;
}
