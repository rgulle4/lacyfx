package cm.controllers;

import cm.models.EPDDatabase;
import javafx.fxml.FXML;
import libs.net.efabrika.util.DBTablePrinter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/9/28.
 */
public class LoadMaterialController {

    // To DO: select alternative materials from database for calculating and comparing
    @FXML
    private void searchbutton() throws SQLException {
        ResultSet r = new EPDDatabase().getResultsFilteredBy("CS >= '7000'");
        DBTablePrinter.printResultSet(r);
    }
}
