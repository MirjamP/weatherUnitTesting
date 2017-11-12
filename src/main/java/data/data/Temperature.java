package data.data;

import lombok.NonNull;
import lombok.Value;

@Value
public class Temperature {
    @NonNull double max;
    @NonNull double min;
    @NonNull double current;

    public Temperature(double current) {
        this.current = current;
        this.max = current;
        this.min = current;
    }
}
