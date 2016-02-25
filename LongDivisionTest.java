package division;

import org.junit.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for LongDivision.
 */
public class LongDivisionTest extends TestCase {
    
	
    public void testCalculateLongDivision(){
    	int dividend = 123;
    	int divisor = 5;
    	String expected = "123|5\n" +
    					  "10" + " +-----\n" +
    					  "   " + "|24,(0)\n" + 
    					  "___\n" + 
    					  " 23\n" + 
    					  " 20\n" + 
    					  "___\n" + 
    					  "  3\n"; 
		String actual = LongDivision.calculateLongDivision(dividend, divisor);
		Assert.assertEquals(expected, actual);
    }

    
}
