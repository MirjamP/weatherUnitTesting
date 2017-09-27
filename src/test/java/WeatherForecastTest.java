import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by mirja on 11/09/2017.
 */
public class WeatherForecastTest {

    @Test
    public void testHttpConnectionToExampleApi() {
        fail();
    }

    @Test
    public void testIfAPIResponseCityEqualsUserRequestCity() {

        try {
            UserRequest userRequest = new UserRequest("linn", "coordinates");
            CurrentWeather currentWeather = new CurrentWeather();
            APIWeatherReport apiWeatherReport = currentWeather.getCurrentWeather(userRequest);
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
}
