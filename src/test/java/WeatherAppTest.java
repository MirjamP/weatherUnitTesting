import data.data.City;
import data.data.CoordinatesOfCity;
import data.data.OneDay;
import data.data.Temperature;
import data.service.APIWeatherForecast;
import data.service.APIWeatherReport;
import data.service.APIWeatherRequest;
import open.OpenWeatherRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.Assert.*;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.*;

/**
 * Created by mirja on 16/12/2017.
 */
public class WeatherAppTest {
    private OpenWeatherRepository mockRepo = mock(OpenWeatherRepository.class);
    private WeatherApp app = new WeatherApp(mockRepo);
    private final City exampleCity = new City("cityName", "cc", new CoordinatesOfCity(1, 1));
    private APIWeatherReport exampleWeatherReport = new APIWeatherReport(exampleCity, new Temperature(10, 0), 5);
    private data.service.APIWeatherForecast exampleForecast = new APIWeatherForecast(exampleCity,
            Arrays.asList(
                    new OneDay(new Temperature(2,7), new Date(0)),
                    new OneDay(new Temperature(2,7), new Date(1000000000)),
                    new OneDay(new Temperature(2,7), new Date(1200000000))
            )
    );

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void analyze() throws Exception {

        @SuppressWarnings("unchecked")
        Function<String, PrintWriter> output = mock(Function.class);
        PrintWriter writer = spy(new PrintWriter(new StringWriter()));
        when(output.apply(any())).thenReturn(writer);
        MockSupplier input = spy(new MockSupplier());
        input.add(new APIWeatherRequest("Tallinn", "ee"));
        input.add(new APIWeatherRequest("Tartu", "eu"));
        when(mockRepo.getCurrentWeather(any())).thenReturn(exampleWeatherReport);
        when(mockRepo.getForecastThreeDays(any())).thenReturn(exampleForecast);

        app.analyze(input,output);
        verify(input, times(3)).get();
        verify(output, times(2)).apply("cityName");
        verify(writer, atLeast(2)).write(anyString());
        verify(writer, atLeast(2)).flush();
    }

    class MockSupplier implements Supplier<APIWeatherRequest> {
        Stack<APIWeatherRequest> inputStack = new Stack<>();

        public void add(APIWeatherRequest req) {
            inputStack.push(req);
        }

        @Override
        public APIWeatherRequest get() {
            try {
                return inputStack.pop();
            } catch (EmptyStackException e) {
                return null;
            }
        }
    }
}