package open;

import data.data.City;
import data.data.OneDay;
import data.service.APIWeatherForecast;
import data.service.APIWeatherReport;
import open.data.*;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mirja on 26/11/2017.
 */
public class OpenWeatherConverterTest {

    OpenWeatherConverter converter = new OpenWeatherConverter();
    OpenWeatherReport inputReport = new OpenWeatherReport();
    OpenWeatherForecast inputForecast = new OpenWeatherForecast();

    @Before
    public void setUp() throws Exception {
        OpenWeatherCoordinates coordinates = new OpenWeatherCoordinates();
        {
            coordinates.setLat(0.5);
            coordinates.setLon(2.5);
        }
        OpenWeatherCity city = new OpenWeatherCity();
        {
            city.setName("Tallinn");
            city.setCoord(coordinates);
            city.setCountry("ee");
        }
        inputForecast.setCity(city);
        ArrayList<OpenWeatherForecastDataPoint> dps = new ArrayList<>();
        {
            OpenWeatherForecastDataPoint dp;
            dp = this.createOpenWeatherDP(0.5, 0.8, 0.2);
            dp.setTimestamp((new Date().getTime() + DateUtils.MILLIS_PER_DAY) / 1000); // tomorrow
            dps.add(dp);
            dp = this.createOpenWeatherDP(0.4, 1.1, 0.4);
            dp.setTimestamp((new Date().getTime() + DateUtils.MILLIS_PER_DAY) / 1000); // tomorrow
            dps.add(dp);
            dp = this.createOpenWeatherDP(5,10,1);
            dp.setTimestamp((new Date().getTime() + DateUtils.MILLIS_PER_DAY*2) / 1000); // in 2 days
            dps.add(dp);
            dp = this.createOpenWeatherDP(0,0,0);
            dp.setTimestamp((new Date().getTime() + DateUtils.MILLIS_PER_DAY*3) / 1000); // in 3 days
            dps.add(dp);
        }
        inputForecast.setDataPoints(dps);

        inputReport.setCity("Tallinn");
        inputReport.setCoordinates(coordinates);
        inputReport.setDt(12345678);
        OpenWeatherMain main = new OpenWeatherMain();
        main.setMin(0);
        main.setMax(5);
        main.setTemp(0.11);
        inputReport.setMain(main);
        OpenWeatherSys sys = new OpenWeatherSys();
        sys.setCountry("ee");
        inputReport.setSys(sys);
    }

    private OpenWeatherForecastDataPoint createOpenWeatherDP(double temp, double max, double min) {
        OpenWeatherForecastDataPoint dp = new OpenWeatherForecastDataPoint();
        OpenWeatherMain main = new OpenWeatherMain();
        {
            main.setTemp(temp);
            main.setMax(max);
            main.setMin(min);
        }
        dp.setTemperature(main);
        return dp;
    }

    @Test
    public void testConvertsForecastCorrectly() throws Exception {
        APIWeatherForecast outputForecast = converter.convert(inputForecast);
        {
            OpenWeatherCity inputCity = inputForecast.getCity();
            City outputCity = outputForecast.getCity();
            Assert.assertEquals(inputCity.getName(), outputCity.getName());
            Assert.assertEquals(inputCity.getName(), outputCity.getName());
            Assert.assertEquals(inputCity.getName(), outputCity.getName());
        }
        List<OneDay> dayReports = outputForecast.getDayReports();
        OneDay tomorrow = dayReports.get(0);
        OneDay tomorrowPlusOne = dayReports.get(1);
        OneDay tomorrowPlusTwo = dayReports.get(2);
        Assert.assertEquals(1.1, tomorrow.getTemperature().getMax(), 0.0);
        Assert.assertEquals(0.2, tomorrow.getTemperature().getMin(), 0.0);
        Assert.assertEquals(1, tomorrowPlusOne.getTemperature().getMin(), 0.0);
        Assert.assertEquals(10, tomorrowPlusOne.getTemperature().getMax(), 0.0);
        Assert.assertEquals(0, tomorrowPlusTwo.getTemperature().getMin(), 0.0);
        Assert.assertEquals(0, tomorrowPlusTwo.getTemperature().getMax(), 0.0);
        // TODO: Check if dates are correct
    }

    @Test
    public void testConvertsReportCorrectly() throws Exception {
        APIWeatherReport outputReport = converter.convert(inputReport);
        Assert.assertEquals(inputReport.getCity(), outputReport.getCity().getName());
        Assert.assertEquals(inputReport.getCoordinates().getLat(), outputReport.getCity().getCoordinates()
                .getLatitude(), 0.0);
        Assert.assertEquals(inputReport.getCoordinates().getLon(), outputReport.getCity().getCoordinates()
                .getLongitude(), 0.0);
        Assert.assertEquals(inputReport.getCountry(), outputReport.getCity().getCountryCode());
        Assert.assertEquals(inputReport.getMain().getTemp(), outputReport.getTemperature(),0.0);
        Assert.assertEquals(inputReport.getMain().getMax(), outputReport.getTemperatureRange().getMax(),0.0);
        Assert.assertEquals(inputReport.getMain().getMin(), outputReport.getTemperatureRange().getMin(),0.0);
    }

}