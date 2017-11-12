package data.data;

import java.util.Date;

import lombok.NonNull;
import lombok.Value;

@Value
public class OneDay {
    @NonNull Temperature temperature;
    @NonNull Date date;
}
