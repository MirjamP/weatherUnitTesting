import data.data.City;
import data.data.CoordinatesOfCity;
import data.service.APIWeatherForecast;
import data.service.APIWeatherRequest;
import open.HttpReader;
import open.JsonConverter;
import open.OpenWeatherConverter;
import open.OpenWeatherRepository;
import open.data.OpenWeatherForecast;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class WeatherForecastTest {


    private OpenWeatherConverter mockConverter = mock(OpenWeatherConverter.class);
    private HttpReader mockHttpReader = mock(HttpReader.class);
    private JsonConverter mockJsonReader = mock(JsonConverter.class);
    private OpenWeatherRepository repo;

    @Before
    public void setUp() throws Exception {
        this.repo = new OpenWeatherRepository(mockHttpReader, mockConverter, mockJsonReader);
    }

    @Test
    public void testGetForecastThreeDaysPassesDataFromReaderToJsonReader() throws Exception {
        InputStreamReader inputStreamReader = mock(InputStreamReader.class);
        when(mockHttpReader.read(any()))
                .thenReturn(inputStreamReader);
        this.repo.getForecastThreeDays(new APIWeatherRequest("Tallinn", "ee"));
        verify(mockHttpReader).read(any());
        verify(mockJsonReader).parseForecast(inputStreamReader);
    }

    @Test
    public void testGetForecastThreeDaysPassesDataFromJsonReaderToConverter() throws Exception {
        OpenWeatherForecast returnedForecast = mock(OpenWeatherForecast.class);
        when(mockJsonReader.parseForecast(any()))
                .thenReturn(returnedForecast);
        this.repo.getForecastThreeDays(new APIWeatherRequest("Tallinn", "ee"));
        verify(mockJsonReader).parseForecast(any());
        verify(mockConverter).convert(returnedForecast);
    }

    @Test
    public void testGetForecastThreeDaysReturnsConverterResult() throws Exception {
        APIWeatherForecast expectedForecast = new APIWeatherForecast( new City("","",new CoordinatesOfCity(0,0)),
                new ArrayList<>());
        when(mockConverter.convert(any(OpenWeatherForecast.class)))
                .thenReturn(expectedForecast);
        APIWeatherForecast actualForecast = this.repo.getForecastThreeDays(new APIWeatherRequest("Tallinn", "ee"));
        Assert.assertEquals(expectedForecast, actualForecast);
    }
}
