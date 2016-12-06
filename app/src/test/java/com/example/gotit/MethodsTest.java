package com.example.gotit;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by othmane on 12/6/2016.
 */
public class MethodsTest {
    @Test
    public void getDay() throws Exception {
        String result = Methods.getDay(1);
        String result2 = Methods.getDay(5);

        assertTrue( result.equalsIgnoreCase("monday"));
        assertTrue( result2.equalsIgnoreCase("friday"));
    }

    @Test
    public void getTime() throws Exception {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("kk:mm");
        String date = sdf.format(c.getTime());

        assertTrue(date.equals(Methods.getTime()));

    }

    @Test
    public void compareTimes() {
        String t1 = "9:00";
        String t2 = "12:00";
        String t3 = "20:00";

        int result = Methods.compareTimes(t1,t2);
        int result2 = Methods.compareTimes(t3, t1);
        assertTrue(result == -1);
        assertTrue(result2 == 1);

    }

    @Test
    public void sendAutoResponse() throws Exception {

    }

}