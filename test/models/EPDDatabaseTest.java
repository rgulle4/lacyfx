package models;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import java.sql.*;

public class EPDDatabaseTest {

    @Test
    public void testGetResultsFilteredBy() throws Exception {
        EPDDatabase epds = new EPDDatabase("test.db");
        ResultSet rs = epds.getResultsFilteredBy("CS >= '7000'");
        int expectedNumRecords = 10;
        Assert.assertEquals(expectedNumRecords, numRecords(rs));
    }

    @Test
    public void testGetResults() throws Exception {
        EPDDatabase epds = new EPDDatabase("test.db");
        ResultSet rsAll = epds.getResults();
        int expectedNumRecords = 880;
        Assert.assertEquals(expectedNumRecords, numRecords(rsAll));
    }

    private int numRecords(ResultSet rs) throws SQLException {
        int numRows = 0;
        while (rs.next())
            numRows++;
        return numRows;
    }
}
