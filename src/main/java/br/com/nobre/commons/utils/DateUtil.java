package br.com.nobre.commons.utils;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date ISOUtcToGMTMinus3 (String dateIso) {
    	
        OffsetDateTime utcDateTime = OffsetDateTime.parse(dateIso, DateTimeFormatter.ISO_DATE_TIME);

        ZonedDateTime gmtMinus3 = utcDateTime.atZoneSameInstant(ZoneOffset.ofHours(-3));
        Date dateInGMTMinus3 = Date.from(gmtMinus3.toInstant());
        
        return dateInGMTMinus3;
        
    }

    public static String GMTMinus3ToISOUtc (Date dateInGMTMinus3) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateInGMTMinus3);
        calendar.add(Calendar.HOUR, 3); 

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String isoDate = format.format(calendar.getTime());

        return isoDate;

    }
    

    public static LocalTime HourUTCToHourGMTMinus3 (String hourPlus3) {

        LocalTime hour = LocalTime.parse(hourPlus3);
        LocalTime hourInGMTMinus3 = hour.minusHours(3);
        
        return hourInGMTMinus3;
        
    }


}
