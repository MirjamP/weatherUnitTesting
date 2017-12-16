package open;

import java.util.Date;
import static org.apache.commons.lang.time.DateUtils.MILLIS_PER_DAY; //TODO: add this via maven, alt-enter

public class DateUtil {
    static Date getSameDay(long timestamp){
        return getSameDay(new Date(timestamp));
    }

    static long daysTo(long timestamp){
        return daysTo(new Date(timestamp));
    }

    static Date getSameDay(Date date){
        long precision = date.getTime() % MILLIS_PER_DAY;
        Date newDate = new Date();
        newDate.setTime(date.getTime() - precision);
        return newDate;
    }

    static long daysTo(Date since){
        long dayTo = since.getTime() / MILLIS_PER_DAY;
        long daynow = new Date().getTime() / MILLIS_PER_DAY;

        return dayTo - daynow;
    }
}
