import org.junit.Test;
import weather.APIWeatherReport;
import weather.CurrentWeather;
import weather.UserRequest;
import weather.ValidateInfo;

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

    //private static Constants.COUNTRY_CODE exampleCountryCode;
    //private static Constants.UNIT exampleUnit;

    @Test
    public void testHttpConnectionToExampleApi() {
        fail();
    }

    @Test
    public void testIfAPIResponseCityEqualsUserRequestCity() {

        try {
            //given
            UserRequest userRequest = new UserRequest("linn", "coordinates");
            CurrentWeather currentWeather = new CurrentWeather();
            //when i try
            APIWeatherReport apiWeatherReport = currentWeather.getCurrentWeather(userRequest);
            //then i expect
            assertEquals(userRequest.getCityName(), apiWeatherReport.getCityName());
        } catch (Exception something) {
            fail("Test failed because " + something.getMessage());
        }
    }

    @Test
    public void testIfAPIResponseGEOEqualsUserRequestGEO() {

        try {
            UserRequest userRequest = new UserRequest("linn", "coordinates");
            CurrentWeather currentWeather = new CurrentWeather();
            APIWeatherReport apiWeatherReport = currentWeather.getCurrentWeather(userRequest);
            assertEquals(userRequest.getCoordinates(), apiWeatherReport.getCoordinates());
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
            UserRequest userRequest = new UserRequest("linn", "coordinates");
            CurrentWeather currentWeather = new CurrentWeather();
            int apiMaxTemp = currentWeather.getMaxTemp(userRequest);
            int apiMinTemp = currentWeather.getMinTemp(userRequest);
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
            UserRequest userRequest = new UserRequest("linn", "coordinates");
            CurrentWeather currentWeather = new CurrentWeather();
            ValidateInfo validateInfo = new ValidateInfo(false, false);
            // [when]
            APIWeatherReport report = currentWeather.getCurrentWeather(userRequest);
            // [then]
            assertTrue(validateInfo.validateTemperature());
        }catch (Exception something){
            fail("Test failed, cause: " + something.getMessage());
        }
    }
}
