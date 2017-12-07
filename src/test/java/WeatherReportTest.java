import com.google.gson.Gson;
import open.HttpReader;
import open.JsonConverter;
import open.OpenWeatherConverter;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import data.service.APIWeatherReport;
import data.service.APIWeatherRequest;
import open.OpenWeatherRepository;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;

//repo on klass, millelt saab küsida infot, nagu küsiks mälust midagi; repo on wrapper APIle
//interface
//testwrapping
//test suite
//postmani URL pmst on see, kust info küsida tuleb:
//http://samples.openweathermap.org/data/2.5/weather?q=Tallinn,ee&appid=8695d243f5c59d68389568a82bdc9e2b

public class WeatherReportTest {

    private OpenWeatherRepository repo;

    @Before
    public void setUp() throws Exception {
        this.repo = new OpenWeatherRepository(new HttpReader(), new OpenWeatherConverter(), new JsonConverter());
    }

    @Test
    @Ignore
    public void testHttpConnectionToExampleApi() {
        fail();
    }

    @Test
    public void testIfAPIResponseCityEqualsUserRequestCity() throws IOException {
        APIWeatherRequest userRequest = new APIWeatherRequest("Tallinn", "ee");
        APIWeatherReport apiWeatherReport = repo.getCurrentWeather(userRequest);
        assertEquals(userRequest.getCityName(), apiWeatherReport.getCity().getName());
    }

    @Test
    public void testIfAPIResponseGEOEqualsUserRequestGEO() throws IOException {
        APIWeatherRequest userRequest = new APIWeatherRequest("Tallinn", "ee");
        APIWeatherReport apiWeatherReport = repo.getCurrentWeather(userRequest);
        assertEquals(userRequest.getCountry().toUpperCase(), apiWeatherReport.getCity().getCountryCode());
    }

    @Test
    @Ignore
    public void testIfInternetConnected() {
        fail();
    }

    @Test
    public void testIfMinIsMinAndMaxIsMaxTemp() throws IOException {
        APIWeatherRequest userRequest = new APIWeatherRequest("Tallinn", "ee");
        APIWeatherReport currentWeather = repo.getCurrentWeather(userRequest);
        double apiMaxTemp = currentWeather.getTemperatureRange().getMax();
        double apiMinTemp = currentWeather.getTemperatureRange().getMin();
        assertTrue(apiMinTemp <= apiMaxTemp);
    }


    @Test
    public void testIfGEOIsInRightFormat() throws IOException {
        APIWeatherRequest userRequest = new APIWeatherRequest("Tallinn", "ee");
        APIWeatherReport apiWeatherReport = repo.getCurrentWeather(userRequest);
        ValidateInfo validateInfo = new ValidateInfo(apiWeatherReport.getTemperatureRange(),
                apiWeatherReport.getCity().getCoordinates());
        assertTrue(validateInfo.validateCoordinates());
    }

    @Test
    public void testHTTPConnection() throws IOException {
        String urlString = "http://httpbin.org/status/200";
        URL url = new URL(urlString);
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        urlConn.connect();
        assertEquals(HttpURLConnection.HTTP_OK, urlConn.getResponseCode());
    }


    @Test
    public void testIfWeatherRepoResponseCurrentTempIsValid() throws IOException {
        APIWeatherRequest userRequest = new APIWeatherRequest("Tallinn", "ee");
        APIWeatherReport apiWeatherReport = repo.getCurrentWeather(userRequest);
        ValidateInfo validateInfo = new ValidateInfo(apiWeatherReport.getTemperatureRange(),
                apiWeatherReport.getCity().getCoordinates());
        assertTrue(validateInfo.validateTemperature());
    }
}
