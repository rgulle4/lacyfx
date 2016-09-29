package cm.controllers;

import cm.models.EPDDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import libs.net.efabrika.util.DBTablePrinter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/9/28.
 */
public class LoadMaterialController {

    // To DO: select alternative materials from database for calculating and comparing
    @FXML
    private TextField TF_CS;

    @FXML
    private void searchbutton() throws SQLException {
        StringBuilder CS_field = new StringBuilder("CS >= ");
        String CS_Textfiled = CS_field.append("'").append(TF_CS.getText()).append("'").toString();

        ResultSet r = new EPDDatabase().getResultsFilteredBy(CS_Textfiled);
        DBTablePrinter.printResultSet(r);
    }
}
