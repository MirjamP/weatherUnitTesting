import org.junit.Before;
import org.junit.Test;

import data.data.Temperature;
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

public class WeatherForecastTest {
    private OpenWeatherRepository repo;

    @Before
    public void setUp() throws Exception {
        this.repo = new OpenWeatherRepository();
    }

    @Test
    public void testHttpConnectionToExampleApi() {
        fail();
    }

    @Test
    public void testIfAPIResponseCityEqualsUserRequestCity() {
        try {
            //given
            APIWeatherRequest userRequest = new APIWeatherRequest("Tallinn", "ee");
            //when i try
            APIWeatherReport apiWeatherReport = repo.getCurrentWeather(userRequest);
            //then i expect
            assertEquals(userRequest.getCityName(), apiWeatherReport.getCity().getName());
        } catch (Exception something) {
            fail("Test failed because " + something.getMessage());
        }
    }

    @Test
    public void testIfAPIResponseGEOEqualsUserRequestGEO() {
        try {
            APIWeatherRequest userRequest = new APIWeatherRequest("Tallinn", "ee");
            APIWeatherReport apiWeatherReport = repo.getCurrentWeather(userRequest);
            assertEquals(userRequest.getCountry(), apiWeatherReport.getCity().getCountryCode());
        } catch (Exception something) {
            fail("Test failed because " + something.getMessage());
        }
    }

    @Test
    public void testIfInternetConnected() {
        fail();
    }

    @Test
    public void testIfMinIsMinAndMaxIsMaxTemp() {

        try {
            APIWeatherRequest userRequest = new APIWeatherRequest("Tallinn", "ee");
            APIWeatherReport currentWeather = repo.getCurrentWeather(userRequest);
            double apiMaxTemp = currentWeather.getTemperature().getMax();
            double apiMinTemp = currentWeather.getTemperature().getMin();
            assertTrue(apiMinTemp < apiMaxTemp);
        } catch (Exception something) {
            fail("Test failed because " + something.getMessage());
        }

    }

    @Test
    public void testIfTempIsInRightFormat() {
        fail();
    }

    @Test
    public void testIfGEOIsInRightFormat() {
        fail();
    }

    @Test
    public void testHTTPConnection() {
        String urlString = "http://stackoverflow.com/about";
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.connect();
            assertEquals(HttpURLConnection.HTTP_OK, urlConn.getResponseCode());
        } catch (IOException something) {
            fail("Test failed because: " + something.getMessage());
        }
    }


    @Test
    //TODO: kontrolli kas temperatuurid on reaalsed (ei ole liiga suur jms)
    public void testIfWeatherRepoResponseCurrentTempIsValid() {
        try{
            // [given]
            APIWeatherRequest userRequest = new APIWeatherRequest("Tallinn", "ee");
            // [when]
            APIWeatherReport apiWeatherReport = repo.getCurrentWeather(userRequest);
            // [then]
            ValidateInfo validateInfo = new ValidateInfo(apiWeatherReport.getTemperature(), apiWeatherReport.getCoordinates());
            assertTrue(validateInfo.validateTemperature());
        }catch (Exception something){
            fail("Test failed, cause: " + something.getMessage());
        }
    }
}
