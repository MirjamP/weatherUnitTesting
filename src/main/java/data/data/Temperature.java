package data.data;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@AllArgsConstructor
public class Temperature {
    @NonNull double max;
    @NonNull double min;

}
