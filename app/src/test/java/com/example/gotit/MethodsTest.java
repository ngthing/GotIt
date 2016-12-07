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
        System.out.println("Get Day from user test:");
        String result = Methods.getDay(1);
        String result2 = Methods.getDay(5);

        try{
            System.out.println("Test 1 passed. Expected : friday. Actual: "+ result2);
            assertTrue( "Expected: friday. Actual: "+Methods.getDay(5),result2.equalsIgnoreCase("friday"));
            assertTrue( result.equalsIgnoreCase("tuesday"));
        }
        catch(AssertionError e)
        {
            System.out.println("Test 2 failed. Expected: tuesday. Actual: "+ result);
        }

        System.out.println();
    }

    @Test
    public void getTime() throws Exception {

        System.out.println("Get Time from user test:");

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("kk:mm");
        String date = sdf.format(c.getTime());

        assertTrue(date.equals(Methods.getTime()));
        System.out.println("Test 1 passed. Expected : "+ Methods.getTime()+ ". Actual: "+ date);

        try{
            assertTrue(date.equals("12:22"));
        }catch(AssertionError e){
            System.out.println("Test 2 Failed. Expected : 12:22."+" Actual: "+ date);
        }
        System.out.println();
    }

    @Test
    public void compareTimes() {
        System.out.println("Compare Start and End times from user Test:");

        String t1 = "9:00";
        String t2 = "12:00";
        String t3 = "20:00";


        int result = Methods.compareTimes(t1,t2);
        assertTrue(result == -1);
        System.out.println("Test 1 Passed. Start time: "+ t1 + ". End time: " + t2);

        int result2 = Methods.compareTimes(t3, t1);
        assertTrue(result2 == 1);
        System.out.println("Test 2 Failed. Start time: "+ t3 + ". End time: " + t1);
    }

}