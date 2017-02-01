package no.nhst;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }


    public void testCmd() throws IOException {
        //String jirakey = "NCPDN-332";
        //String hours = "6h 30m";
        //String description = "Aam sync";
        //String date = "2016-12-01";
        //String[] strings = {jirakey, hours, description, date};
        //String[] cmd = App.createCmd(strings);
        //System.out.println(cmd);
        //String output = App.execCmd(cmd);
        //System.out.println(output);
    }
}
