package cm.models;

import org.junit.Assert;
import org.junit.Test;

import java.sql.*;
import java.util.List;

public class EPDDatabaseTest {

    @Test
    public void testGetZipcode() throws SQLException {
        List<String> distinctOriginZips = (new EPDDatabase()).getZipcode();
        System.out.println("Number of distinct origin zip codes: "
                    + distinctOriginZips.size());
        Assert.assertTrue(distinctOriginZips.size() > 0);
    }

//    @Test
//    public void testGetResultsFilteredBy() throws Exception {
//        EPDDatabase epds = new EPDDatabase("test.db");
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
