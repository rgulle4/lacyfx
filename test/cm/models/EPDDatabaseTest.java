package cm.models;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Assert;
import org.junit.Test;

import java.sql.*;
import java.util.List;

public class EPDDatabaseTest {

    @Test
    public void testEPDDatabase() throws SQLException {
        Assert.assertNotNull(new EPDDatabase());
    }

    @Test
    public void testGetResults() throws SQLException {
        EPDDatabase epds = new EPDDatabase();
        ResultSet rs = epds.getResults();
        int numRecords = numRecords(rs);
        System.out.println("Number of records in epd database: " + numRecords);
        Assert.assertTrue(numRecords > 0);
    }


    @Test
    public void testGetZipcode() throws SQLException {
        // list should have at least 1 distinct zip code
        List<String> result = (new EPDDatabase()).getDistinctZipCodes();

        Assert.assertTrue((result.size() > 0));

        // all list elements should be unique
        boolean allAreUnique = true;
        for (String zip1 : result) {
            int count = 0;
            for (String zip2 : result) {
                if (zip2.equals(zip1))
                    count++;
            }
            if (count > 1)
                allAreUnique = false;
        }

        Assert.assertTrue(allAreUnique);
    }

//    @Test
//    public void testGetResultsFilteredBy() throws Exception {
//        EPDDatabase epds = new EPDDatabase("rt.db");
//        ResultSet rs = epds.getResultsFilteredBy("CS >= '7000'");
//        int expectedNumRecords = 10;
//        Assert.assertEquals(expectedNumRecords, numRecords(rs));
//    }
//
//    @Test
//    public void testGetResults() throws Exception {
//        EPDDatabase epds = new EPDDatabase("rt.db");
//        ResultSet rsAll = epds.getResults();
//        int expectedNumRecords = 880;
//        Assert.assertEquals(expectedNumRecords, numRecords(rsAll));
//    }

    private int numRecords(ResultSet rs) throws SQLException {
        int numRows = 0;
        while (rs.next())
            numRows++;
        return numRows;
    }
}
