/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Xiangtian Gu
 */
public class MovieTest {
    /**
     * Test of getAvgScore method in the normal case.
     */
    @Test
    public void testGetAvgScoreNormal() {  
        Movie instance = new Movie();
        List<Rating> rateList = new ArrayList();
        Rating r1 = new Rating();
        r1.setScore(1);
        Rating r2 = new Rating();
        r2.setScore(2);
        Rating r3 = new Rating();
        r3.setScore(3);
        Rating r4 = new Rating();
        r4.setScore(4);
        Rating r5 = new Rating();
        r5.setScore(5);
        rateList.add(r1);
        rateList.add(r2);
        rateList.add(r3);
        rateList.add(r4);
        rateList.add(r5);      
        instance.setRatings(rateList);
        double expResult = 3.0;
        double result = instance.getAvgScore();
        assertEquals(expResult, result,0.0); 
    }
    /**
     * Test of getAvgScore method, when the rateList is null.
     */
    @Test
    public void testGetAvgScoreNullRating() {  
        Movie instance = new Movie();
        List<Rating> rateList = new ArrayList();
        instance.setRatings(rateList);
        double expResult = 0.0;
        double result = instance.getAvgScore();
        assertEquals(expResult, result,0.0); 
    }
    /**
     * Test of getAvgScore method, when is rateList is empty.
     */
    @Test
    public void testGetAvgScoreEmptyRating() {  
        Movie instance = new Movie();
        List<Rating> rateList = null;
        instance.setRatings(rateList);
        double expResult = 0.0;
        double result = instance.getAvgScore();
        assertEquals(expResult, result,0.0); 
    }
}
